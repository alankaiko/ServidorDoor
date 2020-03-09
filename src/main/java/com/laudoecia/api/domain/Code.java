package com.laudoecia.api.domain;

import javax.persistence.*;

import org.dcm4che3.data.Attributes;

@Entity
@Table
public class Code extends org.dcm4che3.data.Code {
    private static final long serialVersionUID = -130090842318534124L;
    private Long codigo;

    public Code() {}

    public Code(org.dcm4che3.data.Code code) {
        super(code.getCodeValue(), code.getCodingSchemeDesignator(), code.getCodingSchemeVersion(), code.getCodeMeaning());
    }

    public Code(String codeValue, String codingSchemeDesignator,String codingSchemeVersion, String codeMeaning) {
        super(codeValue, codingSchemeDesignator, codingSchemeVersion, codeMeaning);
    }

    public Code(Attributes item) {
        super(item);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getCodigo() {
		return codigo;
	}
    
    public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

    public boolean equals(Code other) {
        return other != null && other.codigo == codigo;
    }

}
