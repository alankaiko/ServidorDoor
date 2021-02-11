package com.laudoecia.api.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.laudoecia.api.modelo.Imagem;
import com.laudoecia.api.repository.ImagemRepository;

@Service
public class ImagemService {
	@Autowired
	private ImagemRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(ImagemService.class);

	
	public List<Imagem> Listar() {
		return this.dao.findAll();
	}

	public Imagem Criar(Imagem imagem) {
		try {
			return this.dao.save(imagem);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ImagemService");
			e.printStackTrace();
			return null;
		}		
	}

	public Imagem BuscarPorId(Long id) {
		Optional<Imagem> imagem = this.dao.findById(id);

		if (imagem.get() == null)
			throw new EmptyResultDataAccessException(1);

		return imagem.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ImagemService");
			e.printStackTrace();
		}
	}

	public void Deletar(Imagem imagem) {
		try {
			this.dao.delete(imagem);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ImagemService");
			e.printStackTrace();
		}
	}

	public Imagem Atualizar(Long id, Imagem imagem) {
		try {
			Imagem salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(imagem, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ImagemService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ImagemService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public Imagem BuscarPeloCodigouid(String codigouid) {
		try {
			return this.dao.findByCodigouid(codigouid);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo BuscarPeloCodigouid------------------ de ImagemService");
			e.printStackTrace();
			return null;
		}		
	}

	public byte[] BuscarImagem(Long codigo){
    	Imagem img = this.BuscarPorId(codigo);
    	
    	byte[] bytes = null;
    	try {
    		InputStream imagem = new FileInputStream(img.getCaminho());
    		bytes = StreamUtils.copyToByteArray(imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}                
        return bytes;
    }
	
	public String BuscarImagemString(Long codigo){
    	Imagem img = this.BuscarPorId(codigo);
    	String encodedString = "";
    	
    	byte[] bytes = null;
    	try {
    		InputStream imagem = new FileInputStream(img.getCaminho());
    		bytes = StreamUtils.copyToByteArray(imagem);

            Base64 base64 = new Base64();
            encodedString = new String(base64.encode(bytes));
            encodedString = "data:image/jpeg;base64," + encodedString;
		} catch (Exception e) {
			e.printStackTrace();
		}                
        return encodedString;
    }
}
