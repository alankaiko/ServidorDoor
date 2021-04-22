package com.laudoecia.api.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laudoecia.api.modelo.Atendimento;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.modelo.ProfissionalExecutante;
import com.laudoecia.api.modelo.SubcategoriaCid10;
import com.laudoecia.api.repository.AtendimentoRepository;
import com.laudoecia.api.repository.filtro.AtendimentoFilter;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@Service
public class AtendimentoService {
	@Autowired
	private AtendimentoRepository dao;
	
	@Autowired
	private SubcategoriaCid10Service cidservice;
	
	@Autowired
	private ProfissionalExecutanteService executaservice;

	private final Logger LOG = LoggerFactory.getLogger(AtendimentoService.class);

	public List<Atendimento> Listar() {
		return this.dao.findAll();
	}
	
	public List<Atendimento> BuscarListaPorId(Long codigo) {
		try {
			return this.dao.findByCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarListaPorId------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Atendimento> BuscarPorNomePaciente(String patientname){
		try {
			return this.dao.findByPacienteNomeStartingWith(patientname);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorNomePaciente------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}

	public Atendimento Criar(Atendimento atendimento) {
		try {
			System.out.println("como sera que vem " + atendimento.getSolicitante().toString());
			atendimento.getProcedimentos().forEach(pro -> pro.setAtendimento(atendimento));
			return this.dao.save(atendimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}

	public Atendimento BuscarPorId(Long id) {
		Optional<Atendimento> atendimento = this.dao.findById(id);

		if (atendimento.get() == null)
			throw new EmptyResultDataAccessException(1);

		return atendimento.get();
	}
	
	public Boolean VerificarSeNomeExiste(AtendimentoFilter filtro) {
		try {
			return this.dao.VerificarPacienteNome(filtro);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo VerificarSeNomeExiste------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}	
	}
	

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPorId------------------ de AtendimentoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Atendimento atendimento) {
		try {
			this.dao.delete(atendimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de AtendimentoService");
			e.printStackTrace();
		}
	}

	public Atendimento Atualizar(Long id, Atendimento atendimento) {
		try {
			Atendimento salvo = this.BuscarPorId(id);
			
			salvo.getProcedimentos().clear();
			salvo.getProcedimentos().addAll(atendimento.getProcedimentos());
			salvo.getProcedimentos().forEach(pro -> pro.setAtendimento(salvo));
			
			BeanUtils.copyProperties(atendimento, salvo, "codigo", "procedimentos");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}

	public Page<Atendimento> Filtrando(AtendimentoFilter filtro, Pageable page) {
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de AtendimentoService");
			e.printStackTrace();
			return null;
		}
	}

	public byte[] AtestadoMontar(Long codigo) throws Exception {
		Atendimento atendimento = this.BuscarPorId(codigo);
		
		System.out.println(atendimento.getCodigodecid());
		SubcategoriaCid10 cid = this.cidservice.BuscarPorId(atendimento.getCodigodecid());
		ProfissionalExecutante executa = this.executaservice.BuscarPorId(atendimento.getCodigoprofexecutante());
		List<Paciente> lista = new ArrayList<Paciente>();
		lista.add(atendimento.getPaciente());
		
		
		Map<String, Object> parametros = new HashMap<>();	
		parametros.put("data", atendimento.getDataatestado());
		parametros.put("cid", cid.getCodigotexto());
		parametros.put("nomecid", cid.getNome());
		parametros.put("executante", executa.getNome());
		String concat = executa.getConselho().getSigla().getDescricao()+" "
				+executa.getConselho().getDescricao()+"-"+executa.getConselho().getEstado().getUf();
		
		parametros.put("crm", concat);
		
		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/Atestado.jasper");	
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(lista));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
