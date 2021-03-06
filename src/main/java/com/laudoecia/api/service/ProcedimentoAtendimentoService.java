package com.laudoecia.api.service;

import java.io.File;
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
import com.laudoecia.api.modelo.ProcedimentoAtendimento;
import com.laudoecia.api.repository.ProcedimentoAtendimentoRepository;
import com.laudoecia.api.utils.ConverterParaJpeg;

@Service
public class ProcedimentoAtendimentoService {
	@Autowired
	ProcedimentoAtendimentoRepository dao;
	
	@Autowired
	LaudoService servicelaudo;
	
	@Autowired
	private ImagemService serviceimagem;
	
	private final Logger LOG = LoggerFactory.getLogger(ProcedimentoAtendimentoService.class);
	
	public List<ProcedimentoAtendimento> Listar() {
		return this.dao.findAll();
	}

	public ProcedimentoAtendimento Criar(ProcedimentoAtendimento procedimento) {
		try {
			return this.dao.save(procedimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	private ProcedimentoAtendimento CriarComPagina(ProcedimentoAtendimento procedimento) {
		try {
			//procedimento.getPaginadeimagens().forEach(pro -> pro.getImagemimpressa().forEach(img -> img.setPaginadeimagens(pro)));
			return this.dao.save(procedimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
			return null;
		}		
	}

	public ProcedimentoAtendimento BuscarPorId(Long id) {
		Optional<ProcedimentoAtendimento> procedimento = this.dao.findById(id);

		if (procedimento.get() == null)
			throw new EmptyResultDataAccessException(1);

		return procedimento.get();
	}
	
	public ProcedimentoAtendimento BuscarPorIdComImg(Long id) {
		Optional<ProcedimentoAtendimento> procedimento = this.dao.findById(id);

		if (procedimento.get() == null)
			throw new EmptyResultDataAccessException(1);
		
		List<Imagem> lista = this.RecuperarListasImagem(procedimento.get().getListaimagem());
		procedimento.get().setListaimagem(lista);

		return procedimento.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
		}
	}

	public void Deletar(ProcedimentoAtendimento procedimento) {
		try {
			this.dao.delete(procedimento);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
		}
	}

	public ProcedimentoAtendimento Atualizar(Long id, ProcedimentoAtendimento procedimento) {
		try {
			ProcedimentoAtendimento salvo = this.BuscarPorId(id);
	
			BeanUtils.copyProperties(procedimento, salvo, "codigo", "listaimagem", "atendimento","paginadeimagens");				
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
			return null;
		}
	}
	
	public ProcedimentoAtendimento AtualizarComImagens(Long id, ProcedimentoAtendimento procedimento) {
		try {
			ProcedimentoAtendimento salvo = this.BuscarPorId(id);
			
			//salvo.getListaimagem().removeAll(procedimento.getListaimagem());
			//this.DeletarImagens(salvo.getListaimagem());
			salvo.getListaimagem().clear();
			
			List<Imagem> listaatualizada = this.GravarListaImagens(procedimento.getListaimagem(),procedimento.getCodigoatdteste());
			salvo.getListaimagem().addAll(listaatualizada);
			salvo.getListaimagem().forEach(lista -> lista.setProcedimentoatendimento(salvo));

			BeanUtils.copyProperties(procedimento, salvo, "codigo", "listaimagem", "atendimento","paginadeimagens");				
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo AtualizarComImagens------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
			return null;
		}
	}
	
	public ProcedimentoAtendimento AtualizarComPaginas(Long id, ProcedimentoAtendimento procedimento) {
		try {
			ProcedimentoAtendimento salvo = this.BuscarPorId(id);
			
			salvo.getPaginadeimagens().clear();
			salvo.getPaginadeimagens().addAll(procedimento.getPaginadeimagens());
			salvo.getPaginadeimagens().forEach(pro -> pro.setProcedimentoatendimento(salvo));
			salvo.getPaginadeimagens().forEach(pro -> pro.getImagemimpressa().forEach(img -> img.setPaginadeimagens(pro)));
					
			BeanUtils.copyProperties(procedimento, salvo, "codigo", "listaimagem", "atendimento","paginadeimagens");				
			return this.CriarComPagina(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ProcedimentoAtendimentoService");
			e.printStackTrace();
			return null;
		}
	}
	
	public void DeletarImagens(List<Imagem> lista) {
		lista.forEach(im -> {
			File file = new File(im.getCaminho());
			
			if(file.exists()) {
				file.delete();
			}		
		});
	}
	
	
	public List<Imagem> GravarListaImagens(List<Imagem> lista, Long codigoatendimento) {
		ConverterParaJpeg converter = new ConverterParaJpeg();
		String caminho = "./arquivo/procedimentos";
		
		this.VerificaSePastaExiste(caminho + "/" + codigoatendimento);

		for(Imagem im : lista) {
			String montarnome = caminho + "/" + codigoatendimento + "/" + im.getNomeimagem() + im.getExtensao();
			im.setCaminho(montarnome);
			converter.CriaImagemNaPasta(montarnome, im.getImagem());
		}
		
		return lista;
	}
	
	public List<Imagem> RecuperarListasImagem(List<Imagem> lista) {
    	try {
    		for(Imagem im : lista) {
    			byte[] bytes = null;
    			InputStream imagem = new FileInputStream(im.getCaminho());
    			bytes = StreamUtils.copyToByteArray(imagem);
    			im.setImagem(bytes);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}   
    	
        return lista;
	}
	
	private void VerificaSePastaExiste(String  caminho) {
  		File file = new File(caminho);
  		
  		if(!file.exists()) {
  			file.mkdirs();
  			//file.setReadable(Boolean.TRUE, Boolean.TRUE);
  			//file.setWritable(Boolean.FALSE, Boolean.TRUE);
  			//file.setExecutable(Boolean.FALSE, Boolean.TRUE);
  		}
  	}

	public byte[] BuscarImagem(Long codigo){
    	Imagem img = this.serviceimagem.BuscarPorId(codigo);
    	
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
    	Imagem img = this.serviceimagem.BuscarPorId(codigo);
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
