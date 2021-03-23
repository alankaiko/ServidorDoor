package com.laudoecia.api.service;

import java.io.InputStream;
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

import com.laudoecia.api.modelo.Convenio;
import com.laudoecia.api.modelo.Licenciado;
import com.laudoecia.api.repository.ConvenioRepository;
import com.laudoecia.api.repository.filtro.ConvenioFilter;
import com.laudoecia.api.repository.resumo.ResumoConvenio;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConvenioService {
	@Autowired
	private ConvenioRepository dao;
	
	@Autowired
	private LicenciadoService licencaservice;
	
	private final Logger LOG = LoggerFactory.getLogger(ConvenioService.class);

	
	public List<Convenio> Listar() {
		return this.dao.findAll();
	}
	
	public List<Convenio> BuscarListaPorId(Long codigo) {
		try {
			return this.dao.findByCodigo(codigo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}
	}

	public Convenio Criar(Convenio convenio) {
		try {
			return this.dao.save(convenio);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}		
	}

	public Convenio BuscarPorId(Long id) {
		Optional<Convenio> convenio = this.dao.findById(id);

		if (convenio.get() == null)
			throw new EmptyResultDataAccessException(1);

		return convenio.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
		}
	}

	public void Deletar(Convenio convenio) {
		try {
			this.dao.delete(convenio);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ConvenioService");
			e.printStackTrace();
		}
	}

	public Convenio Atualizar(Long id, Convenio convenio) {
		try {
			Convenio salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(convenio, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}		
	}

	public Page<ResumoConvenio> Resumindo(ConvenioFilter filtro, Pageable page){
		try {
			return this.dao.resumir(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Resumindo------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}	
	}

	
	public byte[] RelatorioPorPessoa() throws Exception {
		List<Convenio> lista = this.Listar();	
		Licenciado licenca = this.licencaservice.BuscarPorId(1L);
		
		Map<String, Object> parametros = new HashMap<>();	
		parametros.put("NOME_EMPRESA", licenca.getRazaosocial());
		parametros.put("LICENCIADO", licenca.getLicenciadopara());
		
		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/RelConvenios.jasper");	
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(lista));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public Boolean VerificarSeNomeExiste(String nome) {
		try {
			return this.dao.VerificarConvenioNome(nome);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo VerificarSeNomeExiste------------------ de ConvenioService");
			e.printStackTrace();
			return null;
		}	
	}
}
