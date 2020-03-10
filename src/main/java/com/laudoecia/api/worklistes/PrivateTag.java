package com.laudoecia.api.worklistes;

import java.io.Serializable;

public class PrivateTag implements Serializable {

	protected static final int SendingApplicationEntityTitleOfSeries = 0;

	protected static final String PrivateCreator = null;

	public PrivateTag() {
	}

	public PrivateTag(String commonName, String tag, String creator) {
		this.commonName = commonName;
		this.tag = tag;
		this.creator = creator;
	}

	public PrivateTag(String commonName, String tag, String creator, String description) {
		this.commonName = commonName;
		this.tag = tag;
		this.creator = creator;
		this.description = description;
	}

	private String commonName;

	private String tag;

	private String creator;

	private String description;

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getTag() {
		return tag;
	}

	public int getIntTag() {
		return Integer.valueOf(tag, 16);
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setIntTag(int tag) {
		this.tag = Integer.toHexString(tag);
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}