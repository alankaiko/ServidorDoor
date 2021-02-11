package com.laudoecia.api.gerenciarentrada;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private HttpServletResponse resposta;
	private Long id;
	private String codigo;

	public RecursoCriadoEvent(Object obj, HttpServletResponse resposta, Long id) {
		super(obj);
		this.resposta = resposta;
		this.id = id;
	}
	
	public RecursoCriadoEvent(Object obj, HttpServletResponse resposta, String codigo) {
		super(obj);
		this.resposta = resposta;
		this.codigo = codigo;
	}

	public HttpServletResponse getResposta() {
		return resposta;
	}

	public Long getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
