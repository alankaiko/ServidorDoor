package com.laudoecia.api.worklistes;

import java.io.Closeable;
import java.security.cert.X509Certificate;
import java.util.EnumSet;

import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.ApplicationEntityInfo;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.DeviceExtension;
import org.dcm4che3.net.DeviceInfo;
import org.dcm4che3.net.WebApplication;
import org.dcm4che3.net.WebApplicationInfo;

public interface DicomConfiguration extends Closeable {

	WebApplicationInfo[] listWebApplicationInfos(WebApplicationInfo keys) throws ConfigurationException;

	enum Option {
		REGISTER, PRESERVE_VENDOR_DATA, PRESERVE_CERTIFICATE, CONFIGURATION_CHANGES, CONFIGURATION_CHANGES_VERBOSE
	}

	boolean configurationExists() throws ConfigurationException;

	boolean purgeConfiguration() throws ConfigurationException;

	boolean registerAETitle(String aet) throws ConfigurationException;

	boolean registerWebAppName(String webAppName) throws ConfigurationException;

	void unregisterAETitle(String aet) throws ConfigurationException;

	void unregisterWebAppName(String webAppName) throws ConfigurationException;

	ApplicationEntity findApplicationEntity(String aet) throws ConfigurationException;

	WebApplication findWebApplication(String name) throws ConfigurationException;

	DeviceExtension findDevice(String name) throws ConfigurationException;

	DeviceInfo[] listDeviceInfos(DeviceInfo keys) throws ConfigurationException;

	
	ApplicationEntityInfo[] listAETInfos(ApplicationEntityInfo keys) throws ConfigurationException;

	String[] listDeviceNames() throws ConfigurationException;

	String[] listRegisteredAETitles() throws ConfigurationException;

	String[] listRegisteredWebAppNames() throws ConfigurationException;

	ConfigurationChanges persist(Device device, EnumSet<Option> options) throws ConfigurationException;

	ConfigurationChanges merge(Device device, EnumSet<Option> options) throws ConfigurationException;

	ConfigurationChanges removeDevice(String name, EnumSet<Option> options) throws ConfigurationException;

	byte[][] loadDeviceVendorData(String deviceName) throws ConfigurationException;

	ConfigurationChanges updateDeviceVendorData(String deviceName, byte[]... vendorData) throws ConfigurationException;

	String deviceRef(String name);

	void persistCertificates(String ref, X509Certificate... certs) throws ConfigurationException;

	void removeCertificates(String ref) throws ConfigurationException;

	X509Certificate[] findCertificates(String dn) throws ConfigurationException;

	void close();

	void sync() throws ConfigurationException;

	<T> T getDicomConfigurationExtension(Class<T> clazz);
}
