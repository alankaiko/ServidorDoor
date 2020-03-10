package com.laudoecia.api.worklistes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AttributeSet implements Comparable<AttributeSet> {

	public enum Type {
		QIDO_RS, WADO_RS, DIFF_RS, LEADING_CFIND_SCP
	}

	private Type type;
	private String id;
	private String title;
	private String description;
	private int number;
	private int[] selection;
	private boolean installed = true;
	private final Map<String, String> properties = new HashMap<>();

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int[] getSelection() {
		return selection;
	}

	public void setSelection(int[] selection) {
		Arrays.sort(this.selection = selection);
	}

	public void setProperty(String name, String value) {
		properties.put(name, value);
	}

	public String getProperty(String name, String defValue) {
		String value = properties.get(name);
		return value != null ? value : defValue;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(String[] ss) {
		properties.clear();
		for (String s : ss) {
			int index = s.indexOf('=');
			if (index < 0)
				throw new IllegalArgumentException("Property in incorrect format : " + s);
			setProperty(s.substring(0, index), s.substring(index + 1));
		}
	}

	public boolean isInstalled() {
		return installed;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	@Override
	public int compareTo(AttributeSet other) {
		return number - other.number;
	}
}
