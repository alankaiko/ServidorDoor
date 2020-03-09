package com.laudoecia.api.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dcm4che3.data.PersonName.Component;

@Entity
@Table(name = "soundexcode")
public class SoundexCode implements Serializable {
	private static final long serialVersionUID = -6597108452193541679L;

	private Long codigo;
	private org.dcm4che3.data.PersonName.Component personnamecomponent;
	private int componentpartindex;
	private String codevalue;
	private PersonName personname;

	public SoundexCode() {
	}

	public SoundexCode(Component personnamecomponent, int componentpartindex, String codevalue, String nullvalue) {
		this.personnamecomponent = personnamecomponent;
		this.componentpartindex = componentpartindex;
		this.codevalue = codevalue.isEmpty() ? nullvalue : codevalue;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public org.dcm4che3.data.PersonName.Component getPersonnamecomponent() {
		return personnamecomponent;
	}

	public void setPersonnamecomponent(org.dcm4che3.data.PersonName.Component personnamecomponent) {
		this.personnamecomponent = personnamecomponent;
	}

	public int getComponentpartindex() {
		return componentpartindex;
	}

	public void setComponentpartindex(int componentpartindex) {
		this.componentpartindex = componentpartindex;
	}

	public String getCodeValue(String nullValue) {
		if (nullValue == null)
			return codevalue == null ? "" : codevalue;
		else
			return codevalue.equals(nullValue) ? "" : codevalue;
	}
	
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "person_name_fk")
	public PersonName getPersonname() {
		return personname;
	}

	public void setPersonname(PersonName personname) {
		this.personname = personname;
	}

	public String getCodevalue() {
		return codevalue;
	}

	public static Iterator<String> tokenizePersonNameComponent(String name) {
		final StringTokenizer stk = new StringTokenizer(name, " ,-\t");
		return new Iterator<String>() {

			@Override
			public boolean hasNext() {
				return stk.hasMoreTokens();
			}

			@Override
			public String next() {
				return stk.nextToken();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
