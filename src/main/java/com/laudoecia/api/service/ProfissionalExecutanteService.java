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

import com.laudoecia.api.domain.Licenciado;
import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.repository.ProfissionalExecutanteRepository;
import com.laudoecia.api.repository.filtro.ProfissionalExecutanteFilter;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ProfissionalExecutanteService {
	@Autowired
	private ProfissionalExecutanteRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(ProfissionalExecutanteService.class);
	
	@Autowired
	private LicenciadoService licencaservice;
	
	@Autowired
	private ParametrosDoSistemaService paramservice;

	
	public List<ProfissionalExecutante> Listar() {
		return this.dao.findAll();
	}

	public ProfissionalExecutante Criar(ProfissionalExecutante prof) {
		try {
			return this.dao.save(prof);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<ProfissionalExecutante> BuscarListaPorId(String descricao) {
		try {
			return this.dao.findByConselhoDescricao(descricao);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}		
	}

	public ProfissionalExecutante BuscarPorId(Long id) {
		Optional<ProfissionalExecutante> prof = this.dao.findById(id);

		if (prof.get() == null)
			throw new EmptyResultDataAccessException(1);

		return prof.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
		}
	}

	public void Deletar(ProfissionalExecutante prof) {
		try {
			this.dao.delete(prof);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
		}
	}

	public ProfissionalExecutante Atualizar(Long id, ProfissionalExecutante prof) {
		try {
			ProfissionalExecutante salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(prof, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}		
	}

	public Page<ProfissionalExecutante> Resumindo(ProfissionalExecutanteFilter filtro, Pageable page){
		try {
			return this.dao.resumir(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Resumindo------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}	
	}
	
	public Page<ProfissionalExecutante> Filtrando(ProfissionalExecutanteFilter filtro, Pageable page){
		try {
			return this.dao.filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de ProfissionaisExecutantesService");
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<ProfissionalExecutante> BuscarPeloCrmEstado(String descricao, String uf){
		return this.dao.findByConselhoSiglaDescricaoAndConselhoEstadoUf(descricao, uf);
	}

	public byte[] RelatorioPorExecutante(String descricao, String uf) throws Exception{
		List<ProfissionalExecutante> lista = this.BuscarPeloCrmEstado(descricao, uf);	
		
		Licenciado licenca = this.licencaservice.BuscarPorId(1L);		
		
		Map<String, Object> parametros = new HashMap<>();	
		parametros.put("NOME_EMPRESA", licenca.getRazaosocial());
		parametros.put("LICENCIADO", licenca.getLicenciadopara());

		InputStream inputStream = this.getClass().getResourceAsStream("/jasper/RelProfExecutante.jasper");	
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(lista, false));
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
