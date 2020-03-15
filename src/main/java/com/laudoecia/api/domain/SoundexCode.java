
package com.laudoecia.api.domain;

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
@Table
public class SoundexCode {
	private Long codigo;
	private Component personnamecomponent;
	private int componentpartindex;
	private String codevalue;
	private PersonName personname;

	public SoundexCode() {}

	public SoundexCode(Component personNameComponent, int componentPartIndex, String codeValue) {
		this.personnamecomponent = personNameComponent;
		this.componentpartindex = componentPartIndex;
		this.codevalue = codeValue.isEmpty() ? "*" : codeValue;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Component getPersonnamecomponent() {
		return personnamecomponent;
	}

	public void setPersonnamecomponent(Component personnamecomponent) {
		this.personnamecomponent = personnamecomponent;
	}

	public int getComponentpartindex() {
		return componentpartindex;
	}

	public void setComponentpartindex(int componentpartindex) {
		this.componentpartindex = componentpartindex;
	}

	public String getCodevalue() {
		return codevalue;
	}

	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}

	@ManyToOne
	@JoinColumn(name = "person_name_fk")
	public PersonName getPersonname() {
		return personname;
	}

	public void setPersonname(PersonName personname) {
		this.personname = personname;
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
