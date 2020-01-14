package com.laudoecia.api.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laudoecia.api.component.DicomRepost;
import com.laudoecia.api.domain.Dispositive;
import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.domain.Series;
import com.laudoecia.api.domain.Study;
import com.laudoecia.api.domain.Tagimagem;
import com.laudoecia.api.server.DicomReader;
import com.laudoecia.api.service.interf.DBService;
import com.laudoecia.api.utils.DicomEntityBuilder;

@Service
public class DBServiceImpl implements DBService {

	private static final Logger LOG = LoggerFactory.getLogger(DBServiceImpl.class);

	@Autowired
	private InstanceService InstanceService;

	@Autowired
	private SeriesService SeriesService;

	@Autowired
	private StudyService StudyService;

	@Autowired
	private PatientService PatientService;

	@Autowired
	private TagImagemService TagImagemService;

	@Autowired
	private DispositiveService DispositiveService;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private DicomRepost reposter;

	@Transactional
	@Override
	public Patient buildPatient(DicomReader reader) {

		LOG.info("In process; Patient Name: {}, Patient ID: {}", reader.getPatientName(), reader.getPatientID());

		Patient patient = this.PatientService.BuscarPorPacienteId(reader.getPatientID());

		if (patient == null) {// let's create new patient
			patient = DicomEntityBuilder.newPatient(reader.getPatientAge(), reader.getPatientBirthDay(),
					reader.getPatientID(), reader.getPatientName(), reader.getPatientSex());

			this.PatientService.Criar(patient);
			patient = this.PatientService.BuscarPorPacienteId(reader.getPatientID());
		} else {
			LOG.info("Patient already exists; Patient Name: {}, Patient ID: {} ", reader.getPatientName(),
					reader.getPatientID());
		}

		return patient;
	}

	@Transactional
	@Override
	public Study buildStudy(DicomReader reader, Patient patient) {

		// check if study exists
		Study study = this.StudyService.BuscarPorStudyInstanceuid(reader.getStudyInstanceUID());

		if (study == null) {// let's create new study
			study = DicomEntityBuilder.newStudy(reader.getAccessionNumber(), reader.getAdditionalPatientHistory(),
					reader.getAdmittingDiagnosesDescription(), reader.getReferringPhysicianName(),
					reader.getSeriesDateTime(), reader.getStudyID(), reader.getStudyDescription(),
					reader.getStudyInstanceUID(), reader.getStudyPriorityID(), reader.getStudyStatusID());
			study.setPatient(patient);

			this.StudyService.Criar(study);
			study = this.StudyService.BuscarPorStudyInstanceuid(reader.getStudyInstanceUID());
		} else {
			// LOG.info("Study already exists; Study Instance UID: {}",
			// study.getStudyInstanceUID());
		}

		return study;
	}

	@Transactional
	@Override
	public Series buildSeries(DicomReader reader, Study study) {

		// check if series exists
		Series series = this.SeriesService.BuscarPorSeriesInstanceENumber(reader.getSeriesInstanceUID(),
				reader.getSeriesNumber());

		if (series == null) {// let's create new series
			series = DicomEntityBuilder.newSeries(reader.getBodyPartExamined(), reader.getLaterality(),
					reader.getOperatorsName(), reader.getPatientPosition(), reader.getProtocolName(),
					reader.getSeriesDateTime(), reader.getSeriesDescription(), reader.getSeriesInstanceUID(),
					reader.getSeriesNumber());

			series.setStudy(study);
			this.SeriesService.Criar(series);
			series = this.SeriesService.BuscarPorSeriesInstanceENumber(reader.getSeriesInstanceUID(),
					reader.getSeriesNumber());
		} else {
			// LOG.info("Series already exists; Series Instance UID: {}",
			// series.getSeriesInstanceUID());
		}

		return series;
	}

	@Transactional
	@Override
	public Dispositive buildEquipment(DicomReader reader, Series series) {
		Dispositive equipment = this.DispositiveService.BuscarPorSerieEquipamento(series.getIdseries());

		if (equipment == null) {
			equipment = DicomEntityBuilder.newEquipment(reader.getConversionType(), reader.getDeviceSerialNumber(),
					reader.getInstitutionAddress(), reader.getInstitutionName(),
					reader.getInstitutionalDepartmentName(), reader.getManufacturer(),
					reader.getManufacturerModelName(), reader.getModality(), reader.getSoftwareVersion(),
					reader.getStationName());

			equipment.setSeries(series);// set the Series to Equipment because we now have the pkTBLSeriesID
			this.DispositiveService.Criar(equipment);
			equipment = this.DispositiveService.BuscarPorSerieEquipamento(series.getIdseries());

		} else {
			LOG.info("Dispositive já existe; Código do Dispositive {}", equipment.getIddispositive());
		}

		return equipment;
	}

	@Transactional
	@Override
	public Instance buildInstance(DicomReader reader, Series series) {
		Instance instance = this.InstanceService.BuscarPorInstanciaUid(reader.getSOPInstanceUID());

		if (instance == null) {
			instance = DicomEntityBuilder.newInstance(reader.getAcquisitionDateTime(), reader.getContentDateTime(),
					reader.getExposureTime(), reader.getImageOrientation(), reader.getImagePosition(),
					reader.getImageType(), reader.getInstanceNumber(), reader.getKvp(),
					reader.getMediaStorageSopInstanceUID(), reader.getPatientOrientation(), reader.getPixelSpacing(),
					reader.getSliceLocation(), reader.getSliceThickness(), reader.getSopClassUID(),
					reader.getSOPInstanceUID(), reader.getTransferSyntaxUID(), reader.getWindowCenter(),
					reader.getWindowWidth(), reader.getXrayTubeCurrent());

			instance.setSeries(series);
			instance.setTagimagem(this.buildTagImagem(reader));
			this.InstanceService.Criar(instance);
			instance = this.InstanceService.BuscarPorInstanciaUid(reader.getSOPInstanceUID());

		} else {
			LOG.info("Instancia já existe; SOP Instance UID {}, Instance Number {}", instance.getInstancenumber(),
					instance.getInstancenumber());
		}

		return instance;
	}

	// apply dicom logic; patient -> Nxstudy -> Nxseries -> Nxinstance
	@Transactional
	@Override
	public void buildEntities(DicomReader reader) {

		try {
			LOG.info(
					"=================================================================================================================================");
			printStats(reader.getPatientName() + " " + reader.getPatientID() + " " + reader.getPatientAge() + " "
					+ reader.getPatientSex() + " Started");
			Patient patient = buildPatient(reader);
			reposter.add(reader.getMediaStorageSopInstanceUID(), patient.toString());

			if (patient != null) {
				Study study = buildStudy(reader, patient);
				if (study != null) {
					Series series = buildSeries(reader, study);
					if (series != null) {
						Dispositive equipment = buildEquipment(reader, series);// one2one relationship with series
						Instance instance = buildInstance(reader, series);

						// update entity modification dates according to the instance creation
						series.setDatemodify(instance.getDatecreate());
						this.SeriesService.Criar(series);

						equipment.setDatemodify(instance.getDatecreate());
						this.DispositiveService.Criar(equipment);

						study.setDatemodify(instance.getDatecreate());
						this.StudyService.Criar(study);

						patient.setDatemodify(instance.getDatecreate());
						this.PatientService.Criar(patient);

						LOG.info("Dicom Instance saved successfully! {}", instance.toString());
					}
				}
			}

			reposter.remove(reader.getMediaStorageSopInstanceUID());

			printStats(reader.getPatientName() + " " + reader.getPatientID() + " " + reader.getPatientAge() + " "
					+ reader.getPatientSex() + " Ended");
			LOG.info(
					"=================================================================================================================================");
			LOG.info("");

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	public void printStats(String status) {
		LOG.info("{} {} {} [Active Threads: {}] ", Thread.currentThread().getId(), Thread.currentThread().getName(),
				status, Thread.activeCount());

	}

	@Override
	public Tagimagem buildTagImagem(DicomReader reader) {
		
		try {
			Tagimagem tagimagem = DicomEntityBuilder.newTagimagem(reader.getimagetype(),reader.getsopclassuid(), 
					reader.getsopinstanceuid(), reader.getstudydate(), reader.getseriesdate(), reader.getacquisitiontime(),
					reader.getcontentdate(), reader.getstudytime(), reader.getseriestime(), reader.getacquisitiontime(),
					reader.getcontenttime(), reader.getaccessionnumber(), reader.getmodality(), reader.getpresentationintenttype(),
					reader.getmanufacturer(), reader.getinstitutionname(), reader.getinstitutionaddress(), 
					reader.getReferringPhysicianName(), reader.getstationname(), reader.getstudydescription(),
					reader.getseriesdescription(), reader.getinstitutionaldepartmentname(), reader.getperformingphysiciansname(),
					reader.getoperatorsname(), reader.getManufacturerModelName(), reader.getreferencedpatientsequence(),
					reader.getanatomicregionsequence(), reader.getprimaryAnatomicstructuresequence(), reader.getpatientsname(),
					reader.getpatientid(), reader.getSoftwareVersion(), reader.getimagerpixelspacing(),
					reader.getpositionertype(), reader.getdetectortype(), reader.getdetectordescription(), reader.getdetectormode(),
					reader.gettimeoflastdetectorcalibration(), reader.getsamplesperpixel(), reader.getphotometricinterpretation(),
					reader.getrows(), reader.getcolumns());
			
			return this.TagImagemService.Criar(tagimagem);
		} catch (Exception e) {
			LOG.info("Tagimagem erro no DBServiceImpl;");
		}

		return null;
	}

}
