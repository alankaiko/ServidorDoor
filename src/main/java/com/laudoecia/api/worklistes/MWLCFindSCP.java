package com.laudoecia.api.worklistes;

import java.util.EnumSet;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.IDWithIssuer;
import org.dcm4che3.data.NullifyAttributesCoercion;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.TrimISO2020CharacterSetAttributesCoercion;
import org.dcm4che3.data.UID;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.SAXTransformer;
import org.dcm4che3.io.TemplatesCache;
import org.dcm4che3.io.XSLTAttributesCoercion;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCFindSCP;
import org.dcm4che3.net.service.QueryTask;
import org.dcm4che3.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MWLCFindSCP extends BasicCFindSCP {
    private static final Logger LOG = LoggerFactory.getLogger(MWLCFindSCP.class);
    private QueryService queryService;
    private RunInTransaction runInTx= new RunInTransaction();

    public MWLCFindSCP() {
        super(UID.ModalityWorklistInformationModelFIND);
    }

    @Override
    protected QueryTask calculateMatches(Association as, PresentationContext pc, Attributes rq, Attributes keys) {
    	//LOG.info("{}: Process MWL C-FIND RQ:\n{}", as, keys);
    	try {
    		this.queryService = new QueryServiceImpl();
    		String sopClassUID = rq.getString(Tag.AffectedSOPClassUID);
            EnumSet<QueryOption> queryOpts = as.getQueryOptionsFor(sopClassUID);
            QueryContext ctx = queryService.newQueryContextFIND(as, sopClassUID, queryOpts);
            IDWithIssuer idWithIssuer = IDWithIssuer.pidOf(keys);
            if (idWithIssuer != null && !idWithIssuer.getID().equals("*"))
            ctx.setPatientIDs(idWithIssuer);
            ctx.setQueryKeys(keys);
            ctx.setReturnKeys(createReturnKeys(keys));
            coerceAttributes(ctx);
            return new MWLQueryTask(as, pc, rq, keys, queryService.createMWLQuery(ctx), queryService.getAttributesCoercion(ctx), runInTx);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }

    private Attributes createReturnKeys(Attributes keys) {
        Attributes returnKeys = new Attributes(keys.size() + 3);
        returnKeys.addAll(keys);
        returnKeys.setNull(Tag.SpecificCharacterSet, VR.CS);
        return returnKeys;
    }

    private void coerceAttributes(QueryContext ctx) {
    	ArchiveAttributeCoercion rule = new ArchiveAttributeCoercion();
//        ArchiveAttributeCoercion rule = ctx.getArchiveAEExtension().findAttributeCoercion(
//                Dimse.C_FIND_RQ,
//                TransferCapability.Role.SCU,
//                ctx.getSOPClassUID(),
//                ctx.getRemoteHostName(),
//                ctx.getCallingAET(),
//                ctx.getLocalHostName(),
//                ctx.getCalledAET(),
//                ctx.getQueryKeys());
        if (rule == null)
            return;

        AttributesCoercion coercion = null;
        coercion = coerceAttributesByXSL(ctx, rule, coercion);
        coercion = SupplementAssigningAuthorities.forMWL(rule.getSupplementFromDevice(), coercion);
        coercion = rule.supplementIssuerOfPatientID(coercion);
        coercion = rule.nullifyIssuerOfPatientID(ctx.getQueryKeys(), coercion);
        coercion = rule.mergeAttributes(coercion);
        coercion = NullifyAttributesCoercion.valueOf(rule.getNullifyTags(), coercion);
        if (rule.isTrimISO2022CharacterSet())
            coercion = new TrimISO2020CharacterSetAttributesCoercion(coercion);
        coercion = UseCallingAETitleAsCoercion.of(rule.getUseCallingAETitleAs(), ctx.getCallingAET(), coercion);
        if (coercion != null)
            coercion.coerce(ctx.getQueryKeys(), ctx.getCoercedQueryKeys());
    }

    private AttributesCoercion coerceAttributesByXSL(
            QueryContext ctx, ArchiveAttributeCoercion rule, AttributesCoercion next) {
        String xsltStylesheetURI = rule.getXSLTStylesheetURI();
        if (xsltStylesheetURI != null)
            try {
                Templates tpls = TemplatesCache.getDefault().get(StringUtils.replaceSystemProperties(xsltStylesheetURI));
                LOG.info("Coerce Attributes from rule: {}", rule);
                return new XSLTAttributesCoercion(tpls, null)
                        .includeKeyword(!rule.isNoKeywords())
                        .setupTransformer(setupTransformer(ctx));
            } catch (TransformerConfigurationException e) {
                LOG.error("{}: Failed to compile XSL: {}", ctx, xsltStylesheetURI, e);
            }
        return next;
    }

    private SAXTransformer.SetupTransformer setupTransformer(QueryContext ctx) {
        return t -> {
            t.setParameter("LocalAET", ctx.getCalledAET());
            if (ctx.getCallingAET() != null)
                t.setParameter("RemoteAET", ctx.getCallingAET());
            if (ctx.getRemoteHostName() != null)
                t.setParameter("RemoteHost", ctx.getRemoteHostName());
        };
    }
}
