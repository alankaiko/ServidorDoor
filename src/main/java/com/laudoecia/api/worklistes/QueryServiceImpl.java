package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Code;
import org.dcm4che3.data.NullifyAttributesCoercion;
import org.dcm4che3.io.SAXTransformer;
import org.dcm4che3.io.TemplatesCache;
import org.dcm4che3.io.XSLTAttributesCoercion;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.TransferCapability;
import org.dcm4che3.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.laudoecia.api.utils.Utils;


class QueryServiceImpl implements QueryService {
    private static Logger LOG = LoggerFactory.getLogger(QueryServiceImpl.class);
    private EntityManager em;
    QuerySizeEJB querySizeEJB;
    QueryAttributesEJB queryAttributesEJB;
    //private CFindSCU cfindscu;
    //private Event<QueryContext> queryEvent = new AAAATestando();
    
    @Override
    public QueryContext newQueryContextFIND(Association as, String sopClassUID, EnumSet<QueryOption> queryOpts) {
        ApplicationEntity ae = as.getApplicationEntity();
        this.em = Utils.entidade.createEntityManager();
        QueryParam queryParam = new QueryParam(ae);
        queryParam.setCombinedDatetimeMatching(queryOpts.contains(QueryOption.DATETIME));
        queryParam.setFuzzySemanticMatching(queryOpts.contains(QueryOption.FUZZY));
        return new QueryContextImpl(ae, queryParam, this).find(as, sopClassUID);
    }
    
    
    @Override
    public Query createMWLQuery(QueryContext ctx) {
        //queryEvent.fire(ctx);
    	this.em = Utils.entidade.createEntityManager();
        return new MWLQuery(ctx, em);
    }
    
  
    @Override
    public AttributesCoercion getAttributesCoercion(QueryContext ctx) {
        ArchiveAEExtension aeExt = ctx.getArchiveAEExtension();
        aeExt = new ArchiveAEExtension();
        ArchiveAttributeCoercion rule = aeExt.findAttributeCoercion(
                Dimse.C_FIND_RSP,
                TransferCapability.Role.SCU,
                ctx.getSOPClassUID(),
                ctx.getRemoteHostName(),
                ctx.getCallingAET(),
                ctx.getLocalHostName(),
                ctx.getCalledAET(),
                ctx.getQueryKeys());
        if (rule == null)
            return null;

        AttributesCoercion coercion = null;
        String xsltStylesheetURI = rule.getXSLTStylesheetURI();
        if (xsltStylesheetURI != null)
            try {
                Templates tpls = TemplatesCache.getDefault().get(StringUtils.replaceSystemProperties(xsltStylesheetURI));
                coercion = new XSLTAttributesCoercion(tpls, null)
                        .includeKeyword(!rule.isNoKeywords())
                        .setupTransformer(setupTransformer(ctx));
            } catch (TransformerConfigurationException e) {
                LOG.error("{}: Failed to compile XSL: {}", ctx.getAssociation(), xsltStylesheetURI, e);
            }
        coercion = rule.mergeAttributes(coercion);
        coercion = NullifyAttributesCoercion.valueOf(rule.getNullifyTags(), coercion);
//        String leadingCFindSCP = rule.getLeadingCFindSCP();
//        if (leadingCFindSCP != null) {
//            coercion = new CFindSCUAttributeCoercion(ctx.getLocalApplicationEntity(), leadingCFindSCP, rule.getAttributeUpdatePolicy(), cfindscu, leadingCFindSCPQueryCache, coercion);
//        }
        LOG.info("Coerce Attributes from rule: {}", rule);
        return coercion;
    }
   
    private SAXTransformer.SetupTransformer setupTransformer(QueryContext ctx) {
        return t -> {
            t.setParameter("LocalAET", ctx.getCalledAET());
            if (ctx.getCallingAET() != null)
                t.setParameter("RemoteAET", ctx.getCallingAET());

            t.setParameter("RemoteHost", ctx.getRemoteHostName());
        };
    }

	@Override
	public QueryContext newQueryContextQIDO(HttpServletRequestInfo httpRequest, String searchMethod,
			ApplicationEntity ae, QueryParam queryParam) {
		
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
	public CFindSCU cfindSCU() {
		
		return null;
	}

	@Override
	public List<String> getDistinctModalities() {
		
		return null;
	}
}
