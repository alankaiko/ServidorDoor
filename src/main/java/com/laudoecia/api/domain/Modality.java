package com.laudoecia.api.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modality")
public class Modality extends BaseModel{
    private Long codigo;
    private String name;
    private String description;
    private String ip;
    private Integer port;
    private Integer timeout;

    public Modality(Long codigo, String name, String description, String ip, Integer port, Integer timeout) {
        this.codigo = codigo;
        this.name = name;
        this.description = description;
        this.ip = ip;
        this.port = port;
        this.timeout = timeout;
    }

    public Modality() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCodigo() {
		return codigo;
	}
    
    public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
    	if(this.timeout == null)
    		this.timeout = 30;
    	
        this.timeout = timeout;
    }    
}
