package com.laudoecia.api.domain;

import javax.persistence.*;

import org.dcm4che3.data.Attributes;

@Entity
@Table(name = "issuer")
public class Issuer extends org.dcm4che3.data.Issuer {
    private static final long serialVersionUID = -3985937520970392728L;

    private Long codigo;

    public Issuer() {}

    public Issuer(String entityID, String entityUID, String entityUIDType) {
        super(entityID, entityUID, entityUIDType);
    }

    public Issuer(Attributes item) {
        super(item);
    }

    public Issuer(String issuerOfPatientID, Attributes item) {
        super(issuerOfPatientID, item);
    }

    public Issuer(org.dcm4che3.data.Issuer other) {
        super(other);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getCodigo() {
		return codigo;
	}
    
    public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
}
