/*
 * *** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at https://github.com/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * J4Care.
 * Portions created by the Initial Developer are Copyright (C) 2015-2019
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See @authors listed below
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * *** END LICENSE BLOCK *****
 */

package com.laudoecia.api.sistemdicom.interfaces;

import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Code;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.QueryOption;

import com.laudoecia.api.sistemdicom.worklist.Availability;
import com.laudoecia.api.sistemdicom.worklist.HttpServletRequestInfo;
import com.laudoecia.api.sistemdicom.worklist.QueryContext;
import com.laudoecia.api.sistemdicom.worklist.QueryParam;



public interface QueryService {
    QueryContext newQueryContextFIND(Association as, String sopClassUID, EnumSet<QueryOption> queryOpts);
    QueryContext newQueryContextQIDO(HttpServletRequestInfo httpRequest, String searchMethod, ApplicationEntity ae, QueryParam queryParam);
    QueryContext newQueryContext(ApplicationEntity ae, QueryParam queryParam);
    ListaDeTrabalhoQuery createQuery(QueryContext ctx);
    ListaDeTrabalhoQuery createPatientQuery(QueryContext ctx);
    ListaDeTrabalhoQuery createStudyQuery(QueryContext ctx);
    ListaDeTrabalhoQuery createSeriesQuery(QueryContext ctx);
    ListaDeTrabalhoQuery createInstanceQuery(QueryContext ctx);
    ListaDeTrabalhoQuery createMWLQuery(QueryContext ctx);
    ListaDeTrabalhoQuery createUPSQuery(QueryContext ctx);
    ListaDeTrabalhoQuery createUPSWithoutQueryEvent(QueryContext ctx);
    Attributes getSeriesAttributes(QueryContext context, Long seriesPk);
    void addLocationAttributes(Attributes attrs, Long instancePk);
    long calculateStudySize(Long studyPk);
    Attributes createIAN(ApplicationEntity ae, String studyUID, String seriesUID,String[] retrieveAETs, String retrieveLocationUID, Availability availability);
    Attributes createIAN(ApplicationEntity ae, String studyUID, String seriesUID, String sopUID);
    Attributes createXDSiManifest(ApplicationEntity ae, String studyUID,String[] retrieveAETs, String retrieveLocationUID, Code conceptNameCode, int seriesNumber, int instanceNumber, Collection<Attributes> seriesAttrs);
    Attributes createActionInfo(String studyIUID, String seriesIUID, String sopIUID, ApplicationEntity ae);
    Attributes getStudyAttributes(String studyUID);
    List<Object[]> getSeriesInstanceUIDs(String studyUID);
    List<Object[]> getSOPInstanceUIDs(String studyUID);
    List<Object[]> getSOPInstanceUIDs(String studyUID, String seriesUID);
    Integer getNumberOfFrames(String studyInstanceUID, String seriesInstanceUID, String sopInstanceUID);
    ZipInputStream openZipInputStream(QueryContext ctx, String storageID, String storagePath) throws IOException;
    AttributesCoercion getAttributesCoercion(QueryContext ctx);
    List<String> getDistinctModalities();
}
