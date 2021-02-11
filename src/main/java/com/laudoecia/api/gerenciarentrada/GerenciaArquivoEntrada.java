package com.laudoecia.api.gerenciarentrada;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.laudoecia.api.service.interf.DBService;
import com.laudoecia.api.servidor.LeitorDicom;


public class GerenciaArquivoEntrada {
	private static final Logger LOG = LoggerFactory.getLogger(GerenciaArquivoEntrada.class);
		
	@Autowired(required = true)
	private EventBus evento;
	
	@Autowired
	private DBService service;
		
	@Transactional
	@Subscribe
    @AllowConcurrentEvents
    public void TratandoArquivosDeEntrada(Arquivo arquivo) {		
		try{
			File file = arquivo.getArquivo();
			LeitorDicom reader = new LeitorDicom(file);			
			
			synchronized(this.service) {
				this.service.ConstruirEntidade(reader);	
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
	}
	
	@PostConstruct
    public void Constroi(){
        this.evento.register(this);       
    }

    @PreDestroy
    public void Destroi(){
        this.evento.unregister(this);
    }
}
