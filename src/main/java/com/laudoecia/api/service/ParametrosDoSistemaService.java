package com.laudoecia.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.ParametrosDoSistema;
import com.laudoecia.api.repository.ParametrosDoSistemaRepository;

@Service
public class ParametrosDoSistemaService {
	@Autowired
	private ParametrosDoSistemaRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(ParametrosDoSistemaService.class);

	public List<ParametrosDoSistema> Listar() {
		return this.dao.findAll();
	}

	public ParametrosDoSistema Criar(ParametrosDoSistema parametro) {
		try {
			return this.dao.save(parametro);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ParametrosDoSistemaService");
			e.printStackTrace();
			return null;
		}
	}

	public ParametrosDoSistema BuscarPorId(Long id) {
		Optional<ParametrosDoSistema> parametro = this.dao.findById(id);

		if (parametro.get() == null)
			throw new EmptyResultDataAccessException(1);

		return parametro.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ParametrosDoSistemaService");
			e.printStackTrace();
		}
	}

	public void Deletar(ParametrosDoSistema parametro) {
		try {
			this.dao.delete(parametro);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ParametrosDoSistemaService");
			e.printStackTrace();
		}
	}

	public ParametrosDoSistema Atualizar(Long id, ParametrosDoSistema parametro) {
		try {
			ParametrosDoSistema salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(parametro, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ParametrosDoSistemaService");
			e.printStackTrace();
			return null;
		}
	}

	public byte[] BuscarImagem(Long codigo){
		ParametrosDoSistema parametros = this.BuscarPorId(codigo);
    	
		byte[] bytes = null;
    	try {
    		bytes = parametros.getLogomarcalaudo();
		} catch (Exception e) {
			e.printStackTrace();
		}                
        return bytes;
    }
    

}
