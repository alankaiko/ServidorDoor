package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dcm4che3.data.Attributes;

import com.laudoecia.api.utilities.BlobCorruptedException;
import com.laudoecia.api.utils.UtilsWorklist;

@Entity
@Table(name = "attributesblob")
public class AttributesBlob implements Serializable {
	private static final long serialVersionUID = 559808958147016008L;

	private Long codigo;
	private Attributes cachedattributes;

	public AttributesBlob(Attributes attrs) {
		setAttributes(attrs);
	}

	public AttributesBlob() {
		setAttributes(new Attributes());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Transient
	public Attributes getAttributes() throws BlobCorruptedException {
		return cachedattributes;
	}

	public void setAttributes(Attributes attrs) {
		cachedattributes = attrs;
	}

	public boolean equals(AttributesBlob other) {
		return other != null && other.codigo == codigo;
	}

	@Override
	public String toString() {
		return "BLOB[codigo=" + codigo + "]";
	}

	@Basic(optional = false)
	@Column(name = "attrs")
	@Access(AccessType.PROPERTY)
	public byte[] getEncodedAttributes() {
		return UtilsWorklist.encodeAttributes(cachedattributes);
	}

	public void setEncodedAttributes(byte[] atts) {
		cachedattributes = UtilsWorklist.decodeAttributes(atts);
	}

	public Attributes getCachedattributes() {
		return cachedattributes;
	}

	public void setCachedattributes(Attributes cachedattributes) {
		this.cachedattributes = cachedattributes;
	}

	
}
