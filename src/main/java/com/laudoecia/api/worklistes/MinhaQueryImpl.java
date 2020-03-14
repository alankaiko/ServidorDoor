package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Code;
import org.dcm4che3.net.AEExtension;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.QueryOption;

public class MinhaQueryImpl implements QueryService{

	@Override
	public QueryContext newQueryContextFIND(Association as, String sopClassUID, EnumSet<QueryOption> queryOpts) {
		ApplicationEntity entidade = as.getApplicationEntity();
		QueryParam parametros = new QueryParam(entidade);
		parametros.setCombinedDatetimeMatching(queryOpts.contains(QueryOption.DATETIME));
		parametros.setFuzzySemanticMatching(queryOpts.contains(QueryOption.FUZZY));
		return new QueryContextImpl(entidade, parametros, this).find(as, sopClassUID);
	}
	
	

	@Override
	public QueryContext newQueryContextQIDO(HttpServletRequestInfo httpRequest, String searchMethod, ApplicationEntity ae, QueryParam queryParam) {
		
		return null;
	}

	@Override
	public QueryContext newQueryContext(ApplicationEntity ae, QueryParam queryParam) {
		
		return null;
	}

	@Override
	public Query createQuery(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Query createPatientQuery(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Query createStudyQuery(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Query createSeriesQuery(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Query createInstanceQuery(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Query createMWLQuery(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Query createUPSQuery(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Query createUPSWithoutQueryEvent(QueryContext ctx) {
		
		return null;
	}

	@Override
	public Attributes getSeriesAttributes(QueryContext context, Long seriesPk) {
		
		return null;
	}

	@Override
	public void addLocationAttributes(Attributes attrs, Long instancePk) {
		
		
	}

	@Override
	public long calculateStudySize(Long studyPk) {
		
		return 0;
	}

	@Override
	public StudyQueryAttributes calculateStudyQueryAttributes(Long studyPk, QueryRetrieveView qrView) {
		
		return null;
	}

	@Override
	public SeriesQueryAttributes calculateSeriesQueryAttributesIfNotExists(Long seriesPk, QueryRetrieveView qrView) {
		
		return null;
	}

	@Override
	public SeriesQueryAttributes calculateSeriesQueryAttributes(Long seriesPk, QueryRetrieveView qrView) {
		
		return null;
	}

	@Override
	public Attributes getStudyAttributesWithSOPInstanceRefs(String studyUID, ApplicationEntity ae,
			Collection<Attributes> seriesAttrs) {
		
		return null;
	}

	@Override
	public Attributes createIAN(ApplicationEntity ae, String studyUID, String seriesUID, String[] retrieveAETs,
			String retrieveLocationUID, Availability availability) {
		
		return null;
	}

	@Override
	public Attributes createIAN(ApplicationEntity ae, String studyUID, String seriesUID, String sopUID) {
		
		return null;
	}

	@Override
	public Attributes createXDSiManifest(ApplicationEntity ae, String studyUID, String[] retrieveAETs,
			String retrieveLocationUID, Code conceptNameCode, int seriesNumber, int instanceNumber,
			Collection<Attributes> seriesAttrs) {
		
		return null;
	}

	@Override
	public Attributes createRejectionNote(ApplicationEntity ae, String studyUID, String seriesUID, String objectUID,
			RejectionNote rjNote) {
		
		return null;
	}

	@Override
	public Attributes createRejectionNote(Attributes sopInstanceRefs, RejectionNote rjNote) {
		
		return null;
	}

	@Override
	public Attributes createActionInfo(String studyIUID, String seriesIUID, String sopIUID, ApplicationEntity ae) {
		
		return null;
	}

	@Override
	public Attributes queryExportTaskInfo(ExportTask exportTask, ApplicationEntity ae) {
		
		return null;
	}

	@Override
	public Attributes getStudyAttributes(String studyUID) {
		
		return null;
	}

	@Override
	public List<Object[]> getSeriesInstanceUIDs(String studyUID) {
		
		return null;
	}

	@Override
	public List<Object[]> getSOPInstanceUIDs(String studyUID) {
		
		return null;
	}

	@Override
	public List<Object[]> getSOPInstanceUIDs(String studyUID, String seriesUID) {
		
		return null;
	}

	@Override
	public Integer getNumberOfFrames(String studyInstanceUID, String seriesInstanceUID, String sopInstanceUID) {
		
		return null;
	}

	@Override
	public ZipInputStream openZipInputStream(QueryContext ctx, String storageID, String storagePath)
			throws IOException {
		
		return null;
	}

	@Override
	public AttributesCoercion getAttributesCoercion(QueryContext ctx) {
		
		return null;
	}

	@Override
	public CFindSCU cfindSCU() {
		
		return null;
	}

	@Override
	public List<String> getDistinctModalities() {
		
		return null;
	}
	
}
