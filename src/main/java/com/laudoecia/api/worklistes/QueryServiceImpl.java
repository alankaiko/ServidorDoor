package com.laudoecia.api.worklistes;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.ejb.EJBException;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.NullifyAttributesCoercion;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.SAXTransformer;
import org.dcm4che3.io.TemplatesCache;
import org.dcm4che3.io.XSLTAttributesCoercion;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.TransferCapability;
import org.dcm4che3.util.StringUtils;
import org.dcm4che3.util.UIDUtils;


class QueryServiceImpl implements QueryService {

//    private static Logger LOG = LoggerFactory.getLogger(QueryServiceImpl.class);
//    private EntityManager em;
//    private QueryServiceEJB ejb;
//    QuerySizeEJB querySizeEJB;
//    QueryAttributesEJB queryAttributesEJB;
//    private CFindSCU cfindscu;
//    private LeadingCFindSCPQueryCache leadingCFindSCPQueryCache;
//    private CodeCache codeCache;
//    private StorageFactory storageFactory;
//    private Event<QueryContext> queryEvent;

    @Override
    public QueryContext newQueryContextFIND(Association as, String sopClassUID, EnumSet<QueryOption> queryOpts) {
        ApplicationEntity ae = as.getApplicationEntity();
        QueryParam queryParam = new QueryParam(ae);
        queryParam.setCombinedDatetimeMatching(queryOpts.contains(QueryOption.DATETIME));
        queryParam.setFuzzySemanticMatching(queryOpts.contains(QueryOption.FUZZY));
        return new QueryContextImpl(ae, queryParam, this).find(as, sopClassUID);
    }

    @Override
    public QueryContext newQueryContextQIDO(
            HttpServletRequestInfo httpRequest, String searchMethod, ApplicationEntity ae, QueryParam queryParam) {
        return new QueryContextImpl(ae, queryParam, this).qido(httpRequest, searchMethod);
    }

    @Override
    public QueryContext newQueryContext(ApplicationEntity ae, QueryParam queryParam) {
        return new QueryContextImpl(ae, queryParam, this);
    }

    @Override
    public Query createQuery(QueryContext ctx) {
//        queryEvent.fire(ctx);
//        switch (ctx.getQueryRetrieveLevel()) {
//            case PATIENT:
//                return createPatientQuery(ctx);
//            case STUDY:
//                return createStudyQuery(ctx);
//            case SERIES:
//                return createSeriesQuery(ctx);
//            default: // case IMAGE
//                return createInstanceQuery(ctx);
//        }
    	return null;
    }

    @Override
    public Query createPatientQuery(QueryContext ctx) {
        //return new PatientQuery(ctx, em);
    	return null;
    }

    @Override
    public Query createStudyQuery(QueryContext ctx) {
        //return new StudyQuery(ctx, em);
    	return null;
    }

    @Override
    public Query createSeriesQuery(QueryContext ctx) {
        //return new SeriesQuery(ctx, em);
    	return null;
    }

    @Override
    public Query createInstanceQuery(QueryContext ctx) {
        //return new InstanceQuery(ctx, em, codeCache);
    	return null;
    }

    @Override
    public Query createMWLQuery(QueryContext ctx) {
//        queryEvent.fire(ctx);
//        return new MWLQuery(ctx, em);
    	return null;
    }

    @Override
    public Query createUPSQuery(QueryContext ctx) {
//        queryEvent.fire(ctx);
//        return createUPSWithoutQueryEvent(ctx);
    	return null;
    }

    @Override
    public Query createUPSWithoutQueryEvent(QueryContext ctx) {
        //return new UPSQuery(ctx, em);
    	return null;
    }

    @Override
    public Attributes getSeriesAttributes(QueryContext context, Long seriesPk) {
      //  return ejb.getSeriesAttributes(seriesPk, context);
    	return null;
    }

    @Override
    public void addLocationAttributes(Attributes attrs, Long instancePk) {
       // ejb.addLocationAttributes(attrs, instancePk);
    }

    @Override
    public long calculateStudySize(Long studyPk) {
       // return querySizeEJB.calculateStudySize(studyPk);
    	return 0L;
    }

    @Override
    public StudyQueryAttributes calculateStudyQueryAttributes(Long studyPk, QueryRetrieveView qrView) {
        //return queryAttributesEJB.calculateStudyQueryAttributes(studyPk, qrView);
    	return null;
    }

    @Override
    public SeriesQueryAttributes calculateSeriesQueryAttributesIfNotExists(Long seriesPk, QueryRetrieveView qrView) {
        //return ejb.calculateSeriesQueryAttributesIfNotExists(seriesPk, qrView);
    	return null;
    }

    @Override
    public SeriesQueryAttributes calculateSeriesQueryAttributes(Long seriesPk, QueryRetrieveView qrView) {
        //return queryAttributesEJB.calculateSeriesQueryAttributes(seriesPk, qrView);
    	return null;
    }

    @Override
    public Attributes getStudyAttributesWithSOPInstanceRefs(
            String studyUID, ApplicationEntity ae, Collection<Attributes> seriesAttrs) {
        QueryRetrieveView qrView = ae.getAEExtensionNotNull(ArchiveAEExtension.class).getQueryRetrieveView();
//        return ejb.getStudyAttributesWithSOPInstanceRefs(
//                QueryServiceEJB.SOPInstanceRefsType.KOS_XDSI, studyUID, null, null, qrView, seriesAttrs,
//                null, null);
        return null;
    }

    @Override
    public Attributes createIAN(ApplicationEntity ae, String studyUID, String seriesUID,
                                String[] retrieveAETs, String retrieveLocationUID, Availability availability) {
        QueryRetrieveView qrView = ae.getAEExtensionNotNull(ArchiveAEExtension.class).getQueryRetrieveView();
//        return ejb.getSOPInstanceRefs(
//                QueryServiceEJB.SOPInstanceRefsType.IAN, studyUID, seriesUID, null, qrView, null,
//                retrieveAETs, retrieveLocationUID, availability);
        return null;
    }

    @Override
    public Attributes createIAN(ApplicationEntity ae, String studyUID, String seriesUID, String sopUID) {
        QueryRetrieveView qrView = ae.getAEExtensionNotNull(ArchiveAEExtension.class).getQueryRetrieveView();
//        return ejb.getSOPInstanceRefs(
//                QueryServiceEJB.SOPInstanceRefsType.IAN, studyUID, seriesUID, sopUID, qrView, null,
//                null, null, null);
        return null;
    }


    public Attributes createXDSiManifest(ApplicationEntity ae, String studyUID,
                                         String[] retrieveAETs, String retrieveLocationUID,
                                         Code conceptNameCode, int seriesNumber, int instanceNumber,
                                         Collection<Attributes> seriesAttrs) {
        QueryRetrieveView qrView = ae.getAEExtensionNotNull(ArchiveAEExtension.class).getQueryRetrieveView();
//        Attributes attrs = ejb.getStudyAttributesWithSOPInstanceRefs(
//                QueryServiceEJB.SOPInstanceRefsType.KOS_XDSI, studyUID, null, null, qrView, seriesAttrs,
//                retrieveAETs, retrieveLocationUID);
        Attributes attrs = null;
        if (attrs == null || !attrs.containsValue(Tag.CurrentRequestedProcedureEvidenceSequence))
            return null;

        mkKOS(attrs, conceptNameCode, seriesNumber, instanceNumber);
        return attrs;

    }

    @Override
    public Attributes createActionInfo(String studyIUID, String seriesIUID, String sopIUID, ApplicationEntity ae) {
        QueryRetrieveView qrView = ae.getAEExtensionNotNull(ArchiveAEExtension.class).getQueryRetrieveView();
//        return ejb.getSOPInstanceRefs(QueryServiceEJB.SOPInstanceRefsType.STGCMT, studyIUID, seriesIUID, sopIUID, qrView,
//                null, null, null, null);
        return null;
    }

    @Override
    public Attributes queryExportTaskInfo(ExportTask exportTask, ApplicationEntity ae) {
        QueryRetrieveView qrView = ae.getAEExtensionNotNull(ArchiveAEExtension.class).getQueryRetrieveView();
        ArchiveDeviceExtension arcDev = ae.getDevice().getDeviceExtension(ArchiveDeviceExtension.class);
        int retries = arcDev.getStoreUpdateDBMaxRetries();
        for (;;) {
            try {
                if (exportTask.getSeriesInstanceUID().equals("*")) {
 //                   return ejb.queryStudyExportTaskInfo(exportTask.getStudyInstanceUID(), qrView);
                }
//                Attributes attrs = ejb.querySeriesExportTaskInfo(
//                        exportTask.getStudyInstanceUID(),
//                        exportTask.getSeriesInstanceUID(),
//                        qrView);
                Attributes attrs = null;
                if (!exportTask.getSopInstanceUID().equals("*")) {
                    attrs.setInt(Tag.NumberOfStudyRelatedInstances, VR.IS, 1);
                }
                return attrs;
            } catch (EJBException e) {
                if (retries-- > 0) {
 //                   LOG.info("Failed to query Export Task Info for {} - retry:\n", exportTask, e);
                } else {
 //                   LOG.warn("Failed to query Export Task Info for {}:\n", exportTask, e);
                    return null;
                }
            }
            try {
                Thread.sleep(arcDev.storeUpdateDBRetryDelay());
            } catch (InterruptedException e) {
//                LOG.info("Failed to delay retry to query Export Task Info for {}:\n", exportTask, e);
            }
        }
    }

    @Override
    public Attributes createRejectionNote(
            ApplicationEntity ae, String studyUID, String seriesUID, String objectUID, RejectionNote rjNote) {
        QueryRetrieveView qrView = ae.getAEExtensionNotNull(ArchiveAEExtension.class).getQueryRetrieveView();
//        Attributes attrs = ejb.getStudyAttributesWithSOPInstanceRefs(
//                QueryServiceEJB.SOPInstanceRefsType.KOS_IOCM, studyUID, seriesUID, objectUID, qrView,
//                null,null, null);
        Attributes attrs = null;
        if (attrs == null || !attrs.containsValue(Tag.CurrentRequestedProcedureEvidenceSequence))
            return null;

        mkKOS(attrs, rjNote);
        return attrs;
    }

    @Override
    public Attributes createRejectionNote(Attributes sopInstanceRefs, RejectionNote rjNote) {
//        Attributes attrs = ejb.getStudyAttributes(sopInstanceRefs.getString(Tag.StudyInstanceUID));
    	Attributes attrs = null;
        attrs.newSequence(Tag.CurrentRequestedProcedureEvidenceSequence, 1).add(sopInstanceRefs);
        mkKOS(attrs, rjNote);
        return attrs;
    }

    private void mkKOS(Attributes attrs, RejectionNote rjNote) {
        //mkKOS(attrs, rjNote.getRejectionNoteCode(), rjNote.getSeriesNumber(), rjNote.getInstanceNumber());
    }

    @Override
    public Attributes getStudyAttributes(String studyUID) {
       // return ejb.getStudyAttributes(studyUID);
    	return null;
    }

    @Override
    public List<Object[]> getSeriesInstanceUIDs(String studyUID) {
//        return em.createNamedQuery(Series.SERIES_IUIDS_OF_STUDY, Object[].class)
//                .setParameter(1, studyUID)
//                .getResultList();
    	return null;
    }

    @Override
    public List<Object[]> getSOPInstanceUIDs(String studyUID) {
//        return em.createNamedQuery(Instance.IUIDS_OF_STUDY, Object[].class)
//                    .setParameter(1, studyUID)
//                    .getResultList();
    	return null;
    }

    @Override
    public List<Object[]> getSOPInstanceUIDs(String studyUID, String seriesUID) {
//        return em.createNamedQuery(Instance.IUIDS_OF_SERIES, Object[].class)
//                    .setParameter(1, studyUID)
//                    .setParameter(2, seriesUID).getResultList();
    	return null;
    }

    @Override
    public Integer getNumberOfFrames(String studyUID, String seriesUID, String objUID) {
//        return em.createNamedQuery(Instance.NUMBER_OF_FRAMES, Integer.class)
//                .setParameter(1, studyUID)
//                .setParameter(2, seriesUID)
//                .setParameter(3, objUID)
//                .getSingleResult();
    	return null;
    }

    @Override
    public ZipInputStream openZipInputStream(QueryContext ctx, String storageID, String storagePath)
            throws IOException {
//        Storage storage = getStorage(storageID, ctx);
//        return new ZipInputStream(storage.openInputStream(
//                createReadContext(storage, storagePath, ctx.getQueryKeys().getString(Tag.StudyInstanceUID))));
    	return null;
    }

//    private Storage getStorage(String storageID, QueryContext ctx) {
//        Storage storage = ctx.getStorage(storageID);
//        if (storage == null) {
//            ArchiveDeviceExtension arcDev = ctx.getArchiveAEExtension().getArchiveDeviceExtension();
//            storage = storageFactory.getStorage(arcDev.getStorageDescriptorNotNull(storageID));
//            ctx.putStorage(storageID, storage);
//        }
//        return storage;
//    }
//
//    private ReadContext createReadContext(Storage storage, String storagePath, String studyInstanceUID) {
//        ReadContext readContext = storage.createReadContext();
//        readContext.setStoragePath(storagePath);
//        readContext.setStudyInstanceUID(studyInstanceUID);
//        return readContext;
//    }

    private void mkKOS(Attributes attrs, Code conceptNameCode, int seriesNumber, int instanceNumber) {
        Attributes studyRef =  attrs.getNestedDataset(Tag.CurrentRequestedProcedureEvidenceSequence);
        attrs.setString(Tag.SOPClassUID, VR.UI, UID.KeyObjectSelectionDocumentStorage);
        attrs.setString(Tag.SOPInstanceUID, VR.UI, UIDUtils.createUID());
        attrs.setDate(Tag.ContentDateAndTime, new Date());
        attrs.setString(Tag.Modality, VR.CS, "KO");
        attrs.setNull(Tag.ReferencedPerformedProcedureStepSequence, VR.SQ);
        attrs.setString(Tag.SeriesInstanceUID, VR.UI, UIDUtils.createUID());
        attrs.setInt(Tag.SeriesNumber, VR.IS, seriesNumber);
        attrs.setInt(Tag.InstanceNumber, VR.IS, instanceNumber);
        attrs.setString(Tag.ValueType, VR.CS, "CONTAINER");
        attrs.setString(Tag.ContinuityOfContent, VR.CS, "SEPARATE");
        attrs.newSequence(Tag.ConceptNameCodeSequence, 1).add(conceptNameCode.toItem());
        attrs.newSequence(Tag.ContentTemplateSequence, 1).add(templateIdentifier());
        Sequence contentSeq = attrs.newSequence(Tag.ContentSequence, 1);
        for (Attributes seriesRef : studyRef.getSequence(Tag.ReferencedSeriesSequence)) {
            for (Attributes sopRef : seriesRef.getSequence(Tag.ReferencedSOPSequence)) {
                String cuid = sopRef.getString(Tag.ReferencedSOPClassUID);
                String iuid = sopRef.getString(Tag.ReferencedSOPInstanceUID);
                contentSeq.add(contentItem(typeOf(cuid), refSOP(cuid, iuid)));
            }
        }
    }

    private String typeOf(String cuid) {
        String uidName = UID.nameOf(cuid);
        int index = uidName.lastIndexOf(" Storage");
        return uidName.startsWith("Image", index - 5) ? "IMAGE"
            : uidName.startsWith("Waveform", index - 8) ? "WAVEFORM"
            : "COMPOSITE";
    }

    private Attributes templateIdentifier() {
        Attributes attrs = new Attributes(2);
        attrs.setString(Tag.MappingResource, VR.CS, "DCMR");
        attrs.setString(Tag.TemplateIdentifier, VR.CS, "2010");
        return attrs;
    }

    private Attributes contentItem(String valueType, Attributes refSOP) {
        Attributes item = new Attributes(3);
        item.setString(Tag.RelationshipType, VR.CS, "CONTAINS");
        item.setString(Tag.ValueType, VR.CS, valueType);
        item.newSequence(Tag.ReferencedSOPSequence, 1).add(refSOP);
        return item;
    }

    private Attributes refSOP(String cuid, String iuid) {
        Attributes item = new Attributes(2);
        item.setString(Tag.ReferencedSOPClassUID, VR.UI, cuid);
        item.setString(Tag.ReferencedSOPInstanceUID, VR.UI, iuid);
        return item;
    }

    @Override
    public AttributesCoercion getAttributesCoercion(QueryContext ctx) {
        ArchiveAEExtension aeExt = ctx.getArchiveAEExtension();
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
//                LOG.error("{}: Failed to compile XSL: {}", ctx.getAssociation(), xsltStylesheetURI, e);
            }
        coercion = rule.mergeAttributes(coercion);
        coercion = NullifyAttributesCoercion.valueOf(rule.getNullifyTags(), coercion);
        String leadingCFindSCP = rule.getLeadingCFindSCP();
        if (leadingCFindSCP != null) {
            //coercion = new CFindSCUAttributeCoercion(ctx.getLocalApplicationEntity(), leadingCFindSCP,
            //        rule.getAttributeUpdatePolicy(), cfindscu, leadingCFindSCPQueryCache, coercion);
        }
//        LOG.info("Coerce Attributes from rule: {}", rule);
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
    public CFindSCU cfindSCU() {
//        return cfindscu;
    	return null;
    }

    @Override
    public List<String> getDistinctModalities() {
//        return em.createNamedQuery(Series.FIND_DISTINCT_MODALITIES, String.class)
//                .getResultList();
    	return null;
    }

	@Override
	public Attributes createXDSiManifest(ApplicationEntity ae, String studyUID, String[] retrieveAETs,
			String retrieveLocationUID, org.dcm4che3.data.Code conceptNameCode, int seriesNumber, int instanceNumber,
			Collection<Attributes> seriesAttrs) {
		// TODO Auto-generated method stub
		return null;
	}
}
