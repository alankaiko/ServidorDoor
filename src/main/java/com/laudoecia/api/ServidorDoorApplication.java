package com.laudoecia.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

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

	 
	 /************************** Handler for incoming files works with asynchronous event bus initiated by the DicomServer ****************************/    
    @Bean // only one incoming file handler. Even we have multiple DicomServer instances, they all forward files to the same handler...
    public IncomingFileHandler incomingFileHandler(){
        return new IncomingFileHandler();
    }

    @Bean //Guava asynch event bus that initiates 100 fixed thread pool
    public EventBus asyncEventBus(){     
    	this.CriarEntidade();
    	EventBus eventBus =  new AsyncEventBus(java.util.concurrent.Executors.newFixedThreadPool(100));
        return eventBus;
    }

    @Bean //dicom server takes storage output directory, ae title and ports. Server listens same number of ports with same ae title 
    public Map<String, DicomServer> dicomServers(@Value("${pacs.storage.dcm}") String storageDir, @Value("${pacs.aetitle}") String aeTitle, @Value("#{'${pacs.ports}'.split(',')}") List<Integer> ports){
        Map<String, DicomServer> dicomServers = new HashMap<>();

        for (int port:ports) {
            dicomServers.put("DICOM_SERVER_AT_" + port, DicomServer.init(null, port, aeTitle, storageDir, asyncEventBus()));
        }
        return dicomServers;
    }
    /************************** End of Handler for incoming files works with asynchronous event bus initiated by the DicomServer ****************************/
    
    @Bean
    @Qualifier(value = "activeDicoms")
    public DicomRepost activeDicoms(){    
    	return new DicomRepost();    	
    }
    
    
    /************************************************** Database JPA and Hibernate Settings ********************************************************/
    //@Bean //Creating and registering in spring context an entityManager
    public LocalContainerEntityManagerFactoryBean CriarEntidade() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(primaryDataSource());
       // em.setPersistenceUnitName("dbdicom");        
        em.setPackagesToScan(new String[]{"com.laudoecia.api.domain"}); // package where are the @Entity classes are located, usually your domain package
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); // JPA implementation 
        em.setJpaVendorAdapter(vendorAdapter);    

        return em;
    }
    
    //@Bean
    @Primary //configure the primary database
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean //Configuring the transactionManager
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();        
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    /************************************************* End of Database JPA and Hibernate Settings ********************************************************/
      
}
