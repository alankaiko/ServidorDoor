package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;

import org.dcm4che3.io.TemplatesCache;
import org.dcm4che3.net.hl7.HL7DeviceExtension;

import com.laudoecia.api.utilities.FuzzyStr;

public class ArchiveDevice extends HL7DeviceExtension implements ArchiveDeviceMBean {

	private static final long serialVersionUID = 2933279846751009427L;

	private String fuzzyAlgorithmClass;
	private final AttributeFilter[] attributeFilters = new AttributeFilter[Entity.values().length];
	private int configurationStaleTimeout;

	private transient FuzzyStr fuzzyStr;
	private transient TemplatesCache templatesCache;
	private transient DicomConfiguration configuration;

	public ArchiveDevice(String name) {
		super();
	}

	public DicomConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(DicomConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void reloadConfiguration() throws ConfigurationException, IOException, GeneralSecurityException {
		DicomConfiguration tmp = configuration;
		if (tmp == null)
			throw new IllegalStateException("configuration not initalized");
		reconfigure(tmp.findDevice(getDeviceName()));
		bindConnections();
	}

	public String getFuzzyAlgorithmClass() {
		return fuzzyAlgorithmClass;
	}

	public void setFuzzyAlgorithmClass(String fuzzyAlgorithmClass) {
		this.fuzzyStr = fuzzyStr(fuzzyAlgorithmClass);
		this.fuzzyAlgorithmClass = fuzzyAlgorithmClass;
	}

	public FuzzyStr getFuzzyStr() {
		if (fuzzyStr == null)
			if (fuzzyAlgorithmClass == null)
				throw new IllegalStateException("No Fuzzy Algorithm Class configured");
			else
				fuzzyStr = fuzzyStr(fuzzyAlgorithmClass);
		return fuzzyStr;
	}

	private static FuzzyStr fuzzyStr(String s) {
		try {
			return (FuzzyStr) Class.forName(s).newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException(s);
		}
	}

	public int getConfigurationStaleTimeout() {
		return configurationStaleTimeout;
	}

	public void setConfigurationStaleTimeout(int configurationStaleTimeout) {
		this.configurationStaleTimeout = configurationStaleTimeout;
	}

	public void clearTemplatesCache() {
		TemplatesCache cache = templatesCache;
		if (cache != null)
			cache.clear();
	}

	public Templates getTemplates(String uri) throws TransformerConfigurationException {
		TemplatesCache tmp = templatesCache;
		if (tmp == null)
			templatesCache = tmp = new TemplatesCache();
		return tmp.get(uri);
	}

	public void setAttributeFilter(Entity entity, AttributeFilter filter) {
		attributeFilters[entity.ordinal()] = filter;
	}

	public AttributeFilter getAttributeFilter(Entity entity) {
		return attributeFilters[entity.ordinal()];
	}

	public AttributeFilter[] getAttributeFilters() {
		return attributeFilters;
	}

	public StoreParam getStoreParam() {
		StoreParam storeParam = new StoreParam();
		storeParam.setFuzzyStr(fuzzyStr);
		storeParam.setAttributeFilters(attributeFilters);
		return storeParam;
	}
//
//	protected void setDeviceAttributes(Device from) {
//		super.setDeviceAttributes(from);
//		ArchiveDevice arcdev = (ArchiveDevice) from;
//		setFuzzyAlgorithmClass(arcdev.fuzzyAlgorithmClass);
//		setConfigurationStaleTimeout(arcdev.configurationStaleTimeout);
//		System.arraycopy(arcdev.attributeFilters, 0, attributeFilters, 0, attributeFilters.length);
//	}

	@Override
	public String getDeviceName() {
		return null;
	}

	@Override
	public void bindConnections() throws IOException, GeneralSecurityException {
	}

	@Override
	public void unbindConnections() {
	}
}
