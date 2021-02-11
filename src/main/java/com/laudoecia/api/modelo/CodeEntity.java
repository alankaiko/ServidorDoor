package com.laudoecia.api.modelo;

import org.dcm4che3.data.Code;
import org.dcm4che3.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "codeentity")
public class CodeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "pk")
    private long pk;

    @Basic(optional = false)
    @Column(name = "code_value")
    private String codeValue;

    @Basic(optional = false)
    @Column(name = "code_designator")
    private String codingSchemeDesignator;

    @Basic(optional = false)
    @Column(name = "code_version")
    private String codingSchemeVersion;

    @Basic(optional = false)
    @Column(name = "code_meaning")
    private String codeMeaning;

    protected CodeEntity() {} // for JPA

    public CodeEntity(Code code) {
        codeValue = code.getCodeValue();
        codingSchemeDesignator = code.getCodingSchemeDesignator();
        codingSchemeVersion = StringUtils.maskNull(code.getCodingSchemeVersion(), "*");
        codeMeaning = code.getCodeMeaning();
    }

    public long getPk() {
        return pk;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public String getCodingSchemeDesignator() {
        return codingSchemeDesignator;
    }

    public String getCodingSchemeVersion() {
        return StringUtils.nullify(codingSchemeVersion, "*");
    }

    public String getCodeMeaning() {
        return codeMeaning;
    }

    public Code getCode() {
        return new Code(codeValue, codingSchemeDesignator, StringUtils.nullify(codingSchemeVersion, "*"), codeMeaning);
    }

    @Override
    public String toString() {
        return getCode().toString();
    }
}
