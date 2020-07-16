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

import com.laudoecia.api.domain.Atendimento;
import com.laudoecia.api.domain.Convenio;
import com.laudoecia.api.domain.Licenciado;
import com.laudoecia.api.domain.ParametrosDoSistema;
import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.domain.SubcategoriaCid10;
import com.laudoecia.api.repository.AtendimentoRepository;
import com.laudoecia.api.repository.filtro.AtendimentoFilter;
import com.laudoecia.api.repository.filtro.PdfFiltroDados;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AtendimentoService {
	@Autowired
	private AtendimentoRepository dao;
	
	@Autowired
	private ParametrosDoSistemaService serviceparam;
	
	
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
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Atendimento> BuscarPorNomePaciente(String patientname){
		try {
			return this.dao.findByPatientPatientnameStartingWith(patientname);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}
	}

	public Atendimento Criar(Atendimento atendimento) {
		try {
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

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de AtendimentoService");
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
			LOG.error("Erro ao executar o metodo Atualizar------------------ de AbreviaturaService");
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
			LOG.error("Erro ao executar o metodo Filtrando------------------ de AbreviaturaService");
			e.printStackTrace();
			return null;
		}
	}

	public byte[] AtestadoMontar(Long codigo) throws Exception {
		Atendimento atendimento = this.BuscarPorId(codigo);
		
		System.out.println(atendimento.getCodigodecid());
		SubcategoriaCid10 cid = this.cidservice.BuscarPorId(atendimento.getCodigodecid());
		ProfissionalExecutante executa = this.executaservice.BuscarPorId(atendimento.getCodigoprofexecutante());
		List<Patient> lista = new ArrayList<Patient>();
		lista.add(atendimento.getPatient());
		
		
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
	
	public byte[] PdfLaudo(Long codigo, PdfFiltroDados dados) throws Exception {
		try {
			Atendimento atendimento = this.BuscarPorId(codigo);
			
			ParametrosDoSistema parame = this.serviceparam.BuscarPorId(1L);	
			List<ParametrosDoSistema> lista = new ArrayList<ParametrosDoSistema>();
			lista.add(parame);
			
			Map<String, Object> parametros = new HashMap<>();	
			parametros.put("NOME_PACIENTE", atendimento.getPatient().getPatientname());
			parametros.put("COD_ATENDIMENTO", String.format("%07d", atendimento.getCodigo()));
			parametros.put("DATA_ATENDIMENTO", atendimento.getDataatendimento().toString());
			parametros.put("DATA_NASCIMENTO", atendimento.getPatient().getBirthday().toString());
			parametros.put("SOLICITANTE", atendimento.getCodigoprofexecutante());
			parametros.put("CONVENIO", atendimento.getConvenio().getNome());
			parametros.put("PROCEDIMENTO", dados.getProcedimento());
			parametros.put("EXECUTANTE",  dados.getExecutante());
			
			InputStream inputStream = this.getClass().getResourceAsStream("/jasper/PdfLaudo.jasper");	
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(lista));

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
