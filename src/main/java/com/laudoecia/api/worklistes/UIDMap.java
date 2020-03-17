package com.laudoecia.api.worklistes;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table
@Entity
public class UIDMap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk")
	private long pk;

	@Basic(optional = false)
	@Column(name = "uidmap")
	private byte[] encodedMap;

	@Transient
	private Map<String, String> cachedMap;

	public UIDMap() {
	}

	public UIDMap(long pk, byte[] encodedMap) {
		this.pk = pk;
		this.encodedMap = encodedMap;
	}

	@Override
	public String toString() {
		return "UIDMap[pk=" + pk + "]";
	}

	public long getPk() {
		return pk;
	}

	public Map<String, String> getUIDMap() {
		if (cachedMap == null)
			cachedMap = decodeUIDMap(encodedMap);
		return cachedMap;
	}

	public void setUIDMap(Map<String, String> uidmap) {
		encodedMap = encodeUIDMap(cachedMap = uidmap);
	}

	public byte[] getEncodedMap() {
		return encodedMap;
	}

	public static byte[] encodeUIDMap(Map<String, String> uidmap) {
		ByteArrayOutputStream out = new ByteArrayOutputStream(512);
		try (PrintStream ps = new PrintStream(out)) {
			for (Map.Entry<String, String> entry : uidmap.entrySet())
				ps.append(entry.getKey()).append('=').append(entry.getValue()).println();
		}
		return out.toByteArray();
	}

	public static Map<String, String> decodeUIDMap(byte[] b) {
		Map<String, String> result = new HashMap<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(b)));
		String line;
		int delimPos;
		try {
			while ((line = reader.readLine()) != null) {
				delimPos = line.indexOf('=');
				result.put(line.substring(0, delimPos), line.substring(delimPos + 1));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
