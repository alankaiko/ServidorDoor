package com.laudoecia.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.laudoecia.api.component.DicomRepost;
import com.laudoecia.api.handler.IncomingFileHandler;
import com.laudoecia.api.server.DicomServer;

@SpringBootApplication
public class ServidorDoorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorDoorApplication.class, args);
		System.out.println("rodando aqui");
	}

	 
    @Bean
    public IncomingFileHandler incomingFileHandler(){
        return new IncomingFileHandler();
    }

    @Bean
    public EventBus asyncEventBus(){     
    	EventBus eventBus =  new AsyncEventBus(java.util.concurrent.Executors.newFixedThreadPool(100));
        return eventBus;
    }

    @Bean
    public Map<String, DicomServer> dicomServers(@Value("${pacs.storage.dcm}") String storageDir, @Value("${pacs.aetitle}") String aeTitle, @Value("#{'${pacs.ports}'.split(',')}") List<Integer> ports){
        Map<String, DicomServer> dicomServers = new HashMap<>();

        for (int port:ports) {
            dicomServers.put("DICOM_SERVER_AT_" + port, DicomServer.init(null, port, aeTitle, storageDir, asyncEventBus()));
        }
        return dicomServers;
    }

    
    @Bean
    @Qualifier(value = "DicomReposter")
    public DicomRepost activeDicoms(){    
    	return new DicomRepost();    	
    }
   
}
