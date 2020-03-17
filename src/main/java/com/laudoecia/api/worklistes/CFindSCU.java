package com.laudoecia.api.worklistes;

import java.util.EnumSet;
import java.util.List;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.IDWithIssuer;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.DimseRSP;
import org.dcm4che3.net.QueryOption;

public interface CFindSCU {
	List<Attributes> findPatient(ApplicationEntity localAE, String calledAET, int priority, IDWithIssuer pid,
			int... returnKeys) throws Exception;

	List<Attributes> findPatient(Association as, int priority, IDWithIssuer pid, int... returnKeys) throws Exception;

	List<Attributes> findStudiesOfPatient(ApplicationEntity localAE, String calledAET, int priority, IDWithIssuer pid,
			int... returnKeys) throws Exception;

	List<Attributes> find(ApplicationEntity localAE, String calledAET, EnumSet<QueryOption> queryOptions, int priority,
			Attributes keys) throws Exception;

	List<Attributes> findStudy(ApplicationEntity localAE, String calledAET, int priority, String studyIUID,
			int... returnKeys) throws Exception;

	List<Attributes> findStudy(Association as, int priority, String studyIUID, int... returnKeys) throws Exception;

	List<Attributes> findSeries(ApplicationEntity localAE, String calledAET, int priority, String studyIUID,
			String seriesIUID, int... returnKeys) throws Exception;

	List<Attributes> findSeries(Association as, int priority, String studyIUID, String seriesIUID, int... returnKeys)
			throws Exception;

	List<Attributes> findInstance(ApplicationEntity localAE, String calledAET, int priority, String studyIUID,
			String seriesIUID, String sopIUID, int... returnKeys) throws Exception;

	List<Attributes> findInstance(Association as, int priority, String studyIUID, String seriesIUID, String sopIUID,
			int... returnKeys) throws Exception;

	Association openAssociation(ApplicationEntity localAE, String calledAET, String cuid,
			EnumSet<QueryOption> queryOptions) throws Exception;

	DimseRSP query(Association as, int priority, Attributes keys, int autocancel, int capacity,
			Duration splitStudyDateRange) throws Exception;
}
