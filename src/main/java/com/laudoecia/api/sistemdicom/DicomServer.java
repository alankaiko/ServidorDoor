package com.laudoecia.api.sistemdicom;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.media.DicomDirReader;
import org.dcm4che3.media.DicomDirWriter;
import org.dcm4che3.media.RecordFactory;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.AssociationHandler;
import org.dcm4che3.net.AssociationStateException;
import org.dcm4che3.net.Commands;
import org.dcm4che3.net.Connection;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.PDVInputStream;
import org.dcm4che3.net.QueryOption;
import org.dcm4che3.net.State;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.TransferCapability;
import org.dcm4che3.net.pdu.AAssociateAC;
import org.dcm4che3.net.pdu.AAssociateRQ;
import org.dcm4che3.net.pdu.ExtendedNegotiation;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.pdu.UserIdentityAC;
import org.dcm4che3.net.service.AbstractDicomService;
import org.dcm4che3.net.service.BasicCEchoSCP;
import org.dcm4che3.net.service.BasicCFindSCP;
import org.dcm4che3.net.service.BasicCGetSCP;
import org.dcm4che3.net.service.BasicCStoreSCP;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.DicomServiceRegistry;
import org.dcm4che3.net.service.InstanceLocator;
import org.dcm4che3.net.service.QueryRetrieveLevel2;
import org.dcm4che3.net.service.QueryTask;
import org.dcm4che3.net.service.RetrieveTask;
import org.dcm4che3.util.AttributesFormat;
import org.dcm4che3.util.SafeClose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.laudoecia.api.gerenciarentrada.Arquivo;
import com.laudoecia.api.modelo.Imagem;
import com.laudoecia.api.service.manual.ImagemDicomService;
import com.laudoecia.api.sistemdicom.tasks.InstanceQueryTask;
import com.laudoecia.api.sistemdicom.tasks.PatientQueryTask;
import com.laudoecia.api.sistemdicom.tasks.SeriesQueryTask;
import com.laudoecia.api.sistemdicom.tasks.StudyQueryTask;
import com.laudoecia.api.sistemdicom.worklist.MWLCFindSCP;
import com.laudoecia.api.utils.ConversorDicomJPG;

public class DicomServer {
	private static final Logger LOG = LoggerFactory.getLogger(DicomServer.class);
	private static final String DCM_EXT = ".dcm";
	private final Device device = new Device("storescp");
	private final ApplicationEntity ae = new ApplicationEntity("*");
	private final Connection conn = new Connection();
	private File storageDir;
	private int status;
	public EventBus eventBus;
	private File dicomDir;
	private AttributesFormat filePathFormat;
	private RecordFactory recFact;
	private String availability;
	private boolean relationalLenient;
	private boolean stgCmtOnSameAssoc;
	private boolean sendPendingCGet;
	private int sendPendingCMoveInterval;
	private int delayCFind;
	private int delayCStore;
	private int errorCFind;
	private int errorCMove;
	private int errorCGet;
	private boolean ignoreCaseOfPN;
	private boolean matchNoValue;
	private DicomDirReader ddReader;
	private DicomDirWriter ddWriter;
	
	private static final EnumSet<QueryRetrieveLevel2> PATIENT_ROOT_LEVELS = EnumSet.of(
		QueryRetrieveLevel2.PATIENT,
		QueryRetrieveLevel2.STUDY,
		QueryRetrieveLevel2.SERIES,
		QueryRetrieveLevel2.IMAGE
	);
	
	private static final EnumSet<QueryRetrieveLevel2> STUDY_ROOT_LEVELS = EnumSet.of(
		QueryRetrieveLevel2.STUDY,
		QueryRetrieveLevel2.SERIES,
		QueryRetrieveLevel2.IMAGE
	);
	
	private static final EnumSet<QueryRetrieveLevel2> PATIENT_STUDY_ONLY_LEVELS = EnumSet.of(
		QueryRetrieveLevel2.PATIENT,
		QueryRetrieveLevel2.STUDY
	);

	
	private final class CStoreSCPImpl extends BasicCStoreSCP {

		CStoreSCPImpl() {
			super("*");
		}

		@Override
		protected void store(Association as, PresentationContext pc, Attributes rq, PDVInputStream data, Attributes rsp) throws IOException {
			rsp.setInt(Tag.Status, VR.US, status);
			if (storageDir == null)
				return;

			String ipAddress = as.getSocket().getInetAddress().getHostAddress();
			String associationName = as.toString();
			
			String cuid = rq.getString(Tag.AffectedSOPClassUID);
			String iuid = rq.getString(Tag.AffectedSOPInstanceUID);
			String tsuid = pc.getTransferSyntax();

			File file = new File(storageDir, iuid + DCM_EXT);
			
			try {
				LOG.info("as: {}", as);
				storeTo(as, as.createFileMetaInformation(iuid, cuid, tsuid), data, file);
				
				if (!file.exists()) {
					LOG.error("File {} does not exists! Connection Details--> ipAddress: {}  associationName: {}  sopclassuid: {}  sopinstanceuid: {} transfersyntax: {}", file.getAbsolutePath(), ipAddress, associationName, cuid, iuid, tsuid);
					return;
				}
				eventBus.post(new Arquivo(file));
			} catch (EOFException e) {
				LOG.error("Dicom Store EOFException: " + e.getMessage());
			} catch (Exception e) {
				deleteFile(as, file); // broken file, just remove...
				e.printStackTrace();
				//LOG.error("Dicom Store Exception testando: " + e.getMessage());
			}
			
			try {
				CriarImagemJPG(storageDir + "\\" + iuid + ".dcm", iuid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	private final class CFindSCPImpl extends BasicCFindSCP {
		private final EnumSet<QueryRetrieveLevel2> qrLevels;

		public CFindSCPImpl(String sopClass, EnumSet<QueryRetrieveLevel2> qrLevels) {
			super(sopClass);
			this.qrLevels = qrLevels;
		}


		@Override
		protected QueryTask calculateMatches(Association as, PresentationContext pc, Attributes rq, Attributes keys) throws DicomServiceException {
			try {
				
				QueryRetrieveLevel2 level = QueryRetrieveLevel2.validateQueryIdentifier(keys, qrLevels, relational(as, rq));
				if (errorCFind != 0) {
					throw new DicomServiceException(errorCFind);
				}			
				// String ipAddress = as.getSocket().getInetAddress().getHostAddress(); // ip address
				// String associationName = as.toString();
				switch (level) {
				case PATIENT:
					return new PatientQueryTask(as, pc, rq, keys, DicomServer.this);
				case STUDY:
					return new StudyQueryTask(as, pc, rq, keys, DicomServer.this);
				case SERIES:
					return new SeriesQueryTask(as, pc, rq, keys, DicomServer.this);
				case IMAGE:
					return new InstanceQueryTask(as, pc, rq, keys, DicomServer.this);
				default:
					assert true;
				}
				throw new AssertionError();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}		
	
		private boolean relational(Association as, Attributes rq) {
			String cuid = rq.getString(Tag.AffectedSOPClassUID);
			ExtendedNegotiation extNeg = as.getAAssociateAC().getExtNegotiationFor(cuid);
			return QueryOption.toOptions(extNeg).contains(QueryOption.RELATIONAL);
		}
	}
	
	private void CriarImagemJPG(String caminho, String nome) throws IOException {
		File arquivo = new File(caminho);
		File saida = new File("./arquivo/dicomjpeg");

		ConversorDicomJPG conversor = new ConversorDicomJPG();
		String retorno = conversor.Dcm2jpeg(arquivo, saida);	
		
		if(!retorno.isEmpty())
			this.SalvarImagem("./arquivo/dicomjpeg/" + nome, retorno, nome);
		
		arquivo = null;
		saida = null;
	}
	
	private void SalvarImagem(String caminho, String nome, String iuid) {
		try {
			ImagemDicomService service = new ImagemDicomService();
			Imagem imagem = service.BuscandoPorNome(nome);
			
			if(imagem.getCodigo() == null) {
				imagem = new Imagem();
				imagem.setCaminho(caminho + "/" + nome +".jpeg");
				imagem.setExtensao(".jpeg");
				imagem.setCodigouid(iuid);
				imagem.setNomeimagem(nome);
				imagem.setDicom(true);
				
				service.Salvando(imagem);
			} else {
				service.Atualizando(imagem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private final class StgCmtSCPImpl extends AbstractDicomService {
		public StgCmtSCPImpl() {
			super(UID.StorageCommitmentPushModelSOPClass);
		}

		@Override
		public void onDimseRQ(Association as, PresentationContext pc, Dimse dimse, Attributes rq, Attributes actionInfo) throws IOException {
			if (dimse != Dimse.N_ACTION_RQ)
				throw new DicomServiceException(Status.UnrecognizedOperation);
			
			int actionTypeID = rq.getInt(Tag.ActionTypeID, 0);
			if (actionTypeID != 1)
				throw new DicomServiceException(Status.NoSuchActionType).setActionTypeID(actionTypeID);

			Attributes rsp = Commands.mkNActionRSP(rq, Status.Success);
			String callingAET = as.getCallingAET();
			String calledAET = as.getCalledAET();
			Connection remoteConnection = getRemoteConnection(callingAET);
			remoteConnection = conn;
			if (conn == null)
				throw new DicomServiceException(Status.ProcessingFailure, "Unknown Calling AET: " + callingAET);
			Attributes eventInfo = calculateStorageCommitmentResult(calledAET, actionInfo);
			try {
				as.writeDimseRSP(pc, rsp, null);
				device.execute(new SendStgCmtResult(as, eventInfo, stgCmtOnSameAssoc, remoteConnection));
			} catch (AssociationStateException e) {
				LOG.warn("{} << N-ACTION-RSP failed: {}", as, e.getMessage());
			}
		}
	}
	
	
	private final class CGetSCPImpl extends BasicCGetSCP {
		private final EnumSet<QueryRetrieveLevel2> qrLevels;
		private final boolean withoutBulkData;

		public CGetSCPImpl(String sopClass, EnumSet<QueryRetrieveLevel2> qrLevels) {
			super(sopClass);
			this.qrLevels = qrLevels;
			this.withoutBulkData = qrLevels.size() == 1;
		}

		@Override
		protected RetrieveTask calculateMatches(Association as, PresentationContext pc, Attributes rq, Attributes keys) throws DicomServiceException {
			QueryRetrieveLevel2.validateRetrieveIdentifier(keys, qrLevels, relational(as, rq));
			if (errorCGet != 0)
				throw new DicomServiceException(errorCGet);
			List<InstanceLocator> matches = DicomServer.this.calculateMatches(keys);
			if (matches.isEmpty())
				return null;

			RetrieveTaskImpl retrieveTask = new RetrieveTaskImpl(Dimse.C_GET_RQ, as, pc, rq, matches, as, withoutBulkData, delayCStore);
			retrieveTask.setSendPendingRSP(isSendPendingCGet());
			return retrieveTask;
		}

		private boolean relational(Association as, Attributes rq) {
			String cuid = rq.getString(Tag.AffectedSOPClassUID);
			ExtendedNegotiation extNeg = as.getAAssociateAC().getExtNegotiationFor(cuid);
			return QueryOption.toOptions(extNeg).contains(QueryOption.RELATIONAL);
		}

	}

	public List<InstanceLocator> calculateMatches(Attributes keys) throws DicomServiceException {
		try {
			List<InstanceLocator> list = new ArrayList<InstanceLocator>();
			String[] patIDs = keys.getStrings(Tag.PatientID);
			String[] studyIUIDs = keys.getStrings(Tag.StudyInstanceUID);
			String[] seriesIUIDs = keys.getStrings(Tag.SeriesInstanceUID);
			String[] sopIUIDs = keys.getStrings(Tag.SOPInstanceUID);
			DicomDirReader ddr = ddReader;
			Attributes patRec = ddr.findPatientRecord(patIDs);
			while (patRec != null) {
				Attributes studyRec = ddr.findStudyRecord(patRec, studyIUIDs);
				while (studyRec != null) {
					Attributes seriesRec = ddr.findSeriesRecord(studyRec, seriesIUIDs);
					while (seriesRec != null) {
						Attributes instRec = ddr.findLowerInstanceRecord(seriesRec, true, sopIUIDs);
						while (instRec != null) {
							String cuid = instRec.getString(Tag.ReferencedSOPClassUIDInFile);
							String iuid = instRec.getString(Tag.ReferencedSOPInstanceUIDInFile);
							String tsuid = instRec.getString(Tag.ReferencedTransferSyntaxUIDInFile);
							String[] fileIDs = instRec.getStrings(Tag.ReferencedFileID);
							String uri = ddr.toFile(fileIDs).toURI().toString();
							list.add(new InstanceLocator(cuid, iuid, tsuid, uri));
							if (sopIUIDs != null && sopIUIDs.length == 1)
								break;

							instRec = ddr.findNextInstanceRecord(instRec, true, sopIUIDs);
						}
						if (seriesIUIDs != null && seriesIUIDs.length == 1)
							break;

						seriesRec = ddr.findNextSeriesRecord(seriesRec, seriesIUIDs);
					}
					if (studyIUIDs != null && studyIUIDs.length == 1)
						break;

					studyRec = ddr.findNextStudyRecord(studyRec, studyIUIDs);
				}
				if (patIDs != null && patIDs.length == 1)
					break;

				patRec = ddr.findNextPatientRecord(patRec, patIDs);
			}
			return list;
		} catch (IOException e) {
			throw new DicomServiceException(Status.UnableToCalculateNumberOfMatches, e);
		}
	}


	public DicomServer() throws IOException {
		device.setDimseRQHandler(createServiceRegistry());
		device.addConnection(conn);
		device.addApplicationEntity(ae);
		device.setAssociationHandler(associationHandler);
		ae.setAssociationAcceptor(true);
		ae.addConnection(conn);
	}

	private void storeTo(Association as, Attributes fmi, PDVInputStream data, File file) throws IOException {
		LOG.info("{}: M-WRITE {}", as, file);

		file.getParentFile().mkdirs();
		DicomOutputStream out = new DicomOutputStream(file);
		
		try {
			out.writeFileMetaInformation(fmi);
			data.copyTo(out);
		} finally {
			SafeClose.close(out);
		}
	}

	public Attributes calculateStorageCommitmentResult(String calledAET, Attributes actionInfos) throws DicomServiceException {
		Attributes at = null;
		Sequence requestSeq = at.getSequence(Tag.ReferencedSOPSequence);
		int size = 300;
		String[] sopIUIDs = new String[1];
		Attributes eventInfo = new Attributes(6);
		eventInfo.setString(Tag.RetrieveAETitle, VR.AE, calledAET);
		eventInfo.setString(Tag.StorageMediaFileSetID, VR.SH, ddReader.getFileSetID());
		eventInfo.setString(Tag.StorageMediaFileSetUID, VR.SH, ddReader.getFileSetUID());
		eventInfo.setString(Tag.TransactionUID, VR.UI, at.getString(Tag.TransactionUID));
		Sequence successSeq = eventInfo.newSequence(Tag.ReferencedSOPSequence, size);
		Sequence failedSeq = eventInfo.newSequence(Tag.FailedSOPSequence, size);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(size * 4 / 3);
		for (int i = 0; i < sopIUIDs.length; i++) {
			Attributes item = requestSeq.get(i);
			map.put(sopIUIDs[i] = item.getString(Tag.ReferencedSOPInstanceUID), item.getString(Tag.ReferencedSOPClassUID));
		}
		DicomDirReader ddr = ddReader;
		try {
			Attributes patRec = ddr.findPatientRecord();
			while (patRec != null) {
				Attributes studyRec = ddr.findStudyRecord(patRec);
				while (studyRec != null) {
					Attributes seriesRec = ddr.findSeriesRecord(studyRec);
					while (seriesRec != null) {
						Attributes instRec = ddr.findLowerInstanceRecord(seriesRec, true, sopIUIDs);
						while (instRec != null) {
							String iuid = instRec.getString(Tag.ReferencedSOPInstanceUIDInFile);
							String cuid = map.remove(iuid);
							if (cuid.equals(instRec.getString(Tag.ReferencedSOPClassUIDInFile)))
								successSeq.add(refSOP(iuid, cuid, Status.Success));
							else
								failedSeq.add(refSOP(iuid, cuid, Status.ClassInstanceConflict));
							instRec = ddr.findNextInstanceRecord(instRec, true, sopIUIDs);
						}
						seriesRec = ddr.findNextSeriesRecord(seriesRec);
					}
					studyRec = ddr.findNextStudyRecord(studyRec);
				}
				patRec = ddr.findNextPatientRecord(patRec);
			}
		} catch (IOException e) {
			LOG.info("Failed to M-READ " + dicomDir, e);
			throw new DicomServiceException(Status.ProcessingFailure, e);
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			failedSeq.add(refSOP(entry.getKey(), entry.getValue(), Status.NoSuchObjectInstance));
		}
		if (failedSeq.isEmpty())
			eventInfo.remove(Tag.FailedSOPSequence);
		return eventInfo;
	}

	private static Attributes refSOP(String iuid, String cuid, int failureReason) {
		Attributes attrs = new Attributes(3);
		attrs.setString(Tag.ReferencedSOPClassUID, VR.UI, cuid);
		attrs.setString(Tag.ReferencedSOPInstanceUID, VR.UI, iuid);
		if (failureReason != Status.Success)
			attrs.setInt(Tag.FailureReason, VR.US, failureReason);
		return attrs;
	}

	private static void deleteFile(Association as, File file) {
		if (file.delete())
			LOG.info("{}: M-DELETE {}", as, file);
		else
			LOG.warn("{}: M-DELETE {} failed!", as, file);
	}

	private DicomServiceRegistry createServiceRegistry() {
		DicomServiceRegistry serviceRegistry = new DicomServiceRegistry();
		serviceRegistry.addDicomService(new BasicCEchoSCP());
		serviceRegistry.addDicomService(new CStoreSCPImpl());
		serviceRegistry.addDicomService(new StgCmtSCPImpl());
		serviceRegistry.addDicomService(new MWLCFindSCP());
		serviceRegistry.addDicomService(new CFindSCPImpl(UID.PatientRootQueryRetrieveInformationModelFIND, PATIENT_ROOT_LEVELS));
		serviceRegistry.addDicomService(new CFindSCPImpl(UID.StudyRootQueryRetrieveInformationModelFIND, STUDY_ROOT_LEVELS));
		serviceRegistry.addDicomService(new CFindSCPImpl(UID.PatientStudyOnlyQueryRetrieveInformationModelFINDRetired, PATIENT_STUDY_ONLY_LEVELS));
		serviceRegistry.addDicomService(new CGetSCPImpl(UID.PatientRootQueryRetrieveInformationModelGET, PATIENT_ROOT_LEVELS));
		serviceRegistry.addDicomService(new CGetSCPImpl(UID.StudyRootQueryRetrieveInformationModelGET, STUDY_ROOT_LEVELS));
		serviceRegistry.addDicomService(new CGetSCPImpl(UID.PatientStudyOnlyQueryRetrieveInformationModelGETRetired, PATIENT_STUDY_ONLY_LEVELS));
		serviceRegistry.addDicomService(new CGetSCPImpl(UID.CompositeInstanceRetrieveWithoutBulkDataGET, EnumSet.of(QueryRetrieveLevel2.IMAGE)));

		return serviceRegistry;
	}

	public static void configureConn(Connection conn) {
		conn.setReceivePDULength(Connection.DEF_MAX_PDU_LENGTH);
		conn.setSendPDULength(Connection.DEF_MAX_PDU_LENGTH);

		conn.setMaxOpsInvoked(0);
		conn.setMaxOpsPerformed(0);
	}

	public final DicomDirReader getDicomDirReader() {
		return ddReader;
	}

	final DicomDirWriter getDicomDirWriter() {
		return ddWriter;
	}

	public static DicomServer init(String aeHost, int aePort, String aeTitle, String storageDirectory, EventBus eventBus) {
		LOG.info("Bind to: " + aeTitle + "@" + aeHost + ":" + aePort + "; storage: " + storageDirectory);
		DicomServer ds = null;
		try {
			ds = new DicomServer();

			ds.eventBus = eventBus;
			if (aeHost != null) {
				ds.conn.setHostname(aeHost);
			}
			ds.conn.setPort(aePort);
			ds.ae.setAETitle(aeTitle);

			// default conn parameters
			configureConn(ds.conn);

			// accept-unknown
			ds.ae.addTransferCapability(new TransferCapability(null, "*", TransferCapability.Role.SCP, "*"));
			ds.setStorageDirectory(new File(storageDirectory));

			ExecutorService executorService = Executors.newCachedThreadPool();
			ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
			ds.device.setScheduledExecutor(scheduledExecutorService);
			ds.device.setExecutor(executorService);
			ds.device.bindConnections();

		} catch (Exception e) {
			LOG.error("dicomserver: {}", e.getMessage());
			e.printStackTrace();
		}

		return ds;
	}

	private AssociationHandler associationHandler = new AssociationHandler() {

		@Override
		protected AAssociateAC makeAAssociateAC(Association as, AAssociateRQ rq, UserIdentityAC arg2) throws IOException {
			State st = as.getState();
			if (as != null) {
				LOG.info("makeAAssociateAC: {}  Associate State: {}  Associate State Name: {}", as.toString(), st, st.name());
			}

			if (rq != null)
				LOG.info("Max OpsInvoked: {}  Max OpsPerformed: {}  Max PDU Length: {}  Number of Pres. Contexts: {}",
					rq.getMaxOpsInvoked(), rq.getMaxOpsPerformed(), rq.getMaxPDULength(),
					rq.getNumberOfPresentationContexts());

			if (arg2 != null)
				LOG.info("UserIdentityAC Length:{}", arg2.length());

			return super.makeAAssociateAC(as, rq, arg2);
		}

		@Override
		protected AAssociateAC negotiate(Association as, AAssociateRQ rq) throws IOException {
			if (as != null)
				LOG.info("AAssociateAC negotiate:{}", as.toString());
			return super.negotiate(as, rq);
		}

		@Override
		protected void onClose(Association as) {
			State st = as.getState();

			if (as != null && st == State.Sta13) {
				LOG.info("Assocation Released and Closed: {} Name: {}", as.getState(), as.toString());

				try {
					// eventBus.post(new
					// NewLogEvent(as.toString(),st.name(),as.getSocket().getInetAddress().getHostAddress(),
					// null, null, null, null,null,null,null,null));
				} catch (Exception e) {
					LOG.error(e.getMessage());
				}
			} else {
				LOG.info("Association Closed");
			}

			super.onClose(as);
		}
	};
	
	Connection getRemoteConnection(String dest) {
		return this.conn;
	}

	public void setStorageDirectory(File storageDir) {
		if (storageDir != null)
			storageDir.mkdirs();
		this.storageDir = storageDir;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public final void setRecordFactory(RecordFactory recFact) {
		this.recFact = recFact;
	}

	public final RecordFactory getRecordFactory() {
		return recFact;
	}

	public final File getStorageDirectory() {
		return storageDir;
	}

	public final AttributesFormat getFilePathFormat() {
		return filePathFormat;
	}

	public void setFilePathFormat(String pattern) {
		this.filePathFormat = new AttributesFormat(pattern);
	}

	public final File getDicomDirectory() {
		return dicomDir;
	}

	public boolean isWriteable() {
		return storageDir.canWrite();
	}

	public final void setInstanceAvailability(String availability) {
		this.availability = availability;
	}

	public final String getInstanceAvailability() {
		return availability;
	}

	public boolean isIgnoreCaseOfPN() {
		return ignoreCaseOfPN;
	}

	public void setIgnoreCaseOfPN(boolean ignoreCaseOfPN) {
		this.ignoreCaseOfPN = ignoreCaseOfPN;
	}

	public boolean isMatchNoValue() {
		return matchNoValue;
	}

	public void setMatchNoValue(boolean matchNoValue) {
		this.matchNoValue = matchNoValue;
	}

	public boolean isRelationalLenient() {
		return relationalLenient;
	}

	public void setRelationalLenient(boolean relationalLenient) {
		this.relationalLenient = relationalLenient;
	}

	public boolean isStgCmtOnSameAssoc() {
		return stgCmtOnSameAssoc;
	}

	public void setStgCmtOnSameAssoc(boolean stgCmtOnSameAssoc) {
		this.stgCmtOnSameAssoc = stgCmtOnSameAssoc;
	}

	public final void setSendPendingCGet(boolean sendPendingCGet) {
		this.sendPendingCGet = sendPendingCGet;
	}

	public final boolean isSendPendingCGet() {
		return sendPendingCGet;
	}

	public final void setSendPendingCMoveInterval(int sendPendingCMoveInterval) {
		this.sendPendingCMoveInterval = sendPendingCMoveInterval;
	}

	public final int getSendPendingCMoveInterval() {
		return sendPendingCMoveInterval;
	}

	public int getDelayCFind() {
		return delayCFind;
	}

	public void setDelayCFind(int delayCFind) {
		this.delayCFind = delayCFind;
	}

	public int getDelayCStore() {
		return delayCStore;
	}

	public void setDelayCStore(int delayCStore) {
		this.delayCStore = delayCStore;
	}

	public int getErrorCFind() {
		return errorCFind;
	}

	public void setErrorCFind(int errorCFind) {
		this.errorCFind = errorCFind;
	}

	public int getErrorCMove() {
		return errorCMove;
	}

	public void setErrorCMove(int errorCMove) {
		this.errorCMove = errorCMove;
	}

	public int getErrorCGet() {
		return errorCGet;
	}

	public void setErrorCGet(int errorCGet) {
		this.errorCGet = errorCGet;
	}

}