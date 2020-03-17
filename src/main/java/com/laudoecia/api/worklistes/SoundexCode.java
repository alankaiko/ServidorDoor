package com.laudoecia.api.worklistes;

import java.util.Iterator;
import java.util.StringTokenizer;

import org.dcm4che3.data.PersonName.Component;

import com.laudoecia.api.domain.PersonName;

public class SoundexCode {

	private long pk;
	private Component personNameComponent;
	private int componentPartIndex;
	private String codeValue;
	private PersonName personName;

	public SoundexCode() {
	}

	public SoundexCode(Component personNameComponent, int componentPartIndex, String codeValue) {
		this.personNameComponent = personNameComponent;
		this.componentPartIndex = componentPartIndex;
		this.codeValue = codeValue.isEmpty() ? "*" : codeValue;
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

	public long getPk() {
		return pk;
	}

	public Component getPersonNameComponent() {
		return personNameComponent;
	}

	public int getComponentPartIndex() {
		return componentPartIndex;
	}

	public String getCodeValue() {
		return codeValue.equals("*") ? "" : codeValue;
	}

	public PersonName getPersonName() {
		return personName;
	}

	public void setPersonName(PersonName personName) {
		this.personName = personName;
	}

}
