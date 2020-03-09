package org.dcm4chee.archive.entity;

import javax.persistence.*;

import org.dcm4che3.data.Attributes;

@Entity
@Table(name = "code", uniqueConstraints = @UniqueConstraint(columnNames = {"code_value","code_designator","code_version"})
)
public class Code extends org.dcm4che3.data.Code {
    private static final long serialVersionUID = -130090842318534124L;
    public static final String FIND_BY_CODE_VALUE_WITHOUT_SCHEME_VERSION = "Code.findByCodeValueWithoutSchemeVersion";
    public static final String FIND_BY_CODE_VALUE_WITH_SCHEME_VERSION = "Code.findByCodeValueWithSchemeVersion";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long codigo;

    public Code() {}

    public Code(org.dcm4che3.data.Code code) {
        super(code.getCodeValue(), code.getCodingSchemeDesignator(), code.getCodingSchemeVersion(), code.getCodeMeaning());
    }

    public Code(String codeValue, String codingSchemeDesignator, String codingSchemeVersion, String codeMeaning) {
        super(codeValue, codingSchemeDesignator, codingSchemeVersion, codeMeaning);
    }

    public Code(Attributes item) {
        super(item);
    }

    public Long getCodigo() {
		return codigo;
	}
    
    public boolean equals(Code other) {
        return other != null && other.codigo == codigo;
    }

}
