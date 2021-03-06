package com.laudoecia.api.utils;

import java.util.Date;

import com.laudoecia.api.modelo.Equipamento;
import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.modelo.Series;
import com.laudoecia.api.modelo.Tagimagem;

public class DicomEntityBuilder {

	public static Paciente NovoPaciente(String idade, Date dataaniversario, String pacienteid, String nome,
		String sexo, Date datacriacao, String tamanho, String peso) {
		
		Paciente paciente = new Paciente();
		paciente.setIdade(idade);
		paciente.setDatanasc(Utils.ConverterToLocalDate(dataaniversario));
		paciente.setDatacriacao(Utils.ConverterToLocalDate(datacriacao));
		paciente.setPacienteid(pacienteid);
		paciente.setNome(nome);
		paciente.setTamanho(tamanho);
		paciente.setPeso(peso);
		paciente.setSexo(Utils.VerificaSexo(sexo));
		
		return paciente;
	}

	public static Estudo NovoEstudo(String accessionNumber, String additionalPatientHistory,
			String admittingDiagnosesDescription, String referringPhysicianName, Date studyDateTime, String studyID,
			String studyDescription, String studyInstanceUID, String studyPriorityID, String studyStatusID) {

		Estudo study = new Estudo();
		study.setAccessionnumber(accessionNumber);
		study.setAdditionalpatienthistory(additionalPatientHistory);
		study.setAdmittingdiagnosesdescription(admittingDiagnosesDescription);
		study.setReferringphysicianname(referringPhysicianName);
		study.setStudydatetime(studyDateTime);
		study.setStudyid(studyID);
		study.setStudydescription(studyDescription);
		study.setStudyinstanceuid(studyInstanceUID);
		study.setStudypriorityid(studyPriorityID);
		study.setStudystatusid(studyStatusID);

		return study;
	}

	public static Series NovaSerie(String bodyPartExamined, String laterality, String operatorsName,
			String patientPosition, String protocolName, Date seriesDateTime, String seriesDescription,
			String seriesInstanceUID, Integer seriesNumber) {

		Series series = new Series();
		series.setBodypartexamined(bodyPartExamined);
		series.setLaterality(laterality);
		series.setOperatorsname(operatorsName);
		series.setPatientposition(patientPosition);
		series.setProtocolname(protocolName);
		series.setSeriesdatetime(seriesDateTime);
		series.setSeriesdescription(seriesDescription);
		series.setSeriesinstanceuid(seriesInstanceUID);
		series.setSeriesnumber(seriesNumber);

		return series;
	}

	public static Equipamento NovoEquipamento(String conversionType, String deviceSerialNumber, String institutionAddress,
			String institutionName, String institutionalDepartmentName, String manufacturer,
			String manufacturerModelName, String modality, String softwareVersion, String stationName) {

		Equipamento equipment = new Equipamento();
		equipment.setConversiontype(conversionType);
		equipment.setSerial(deviceSerialNumber);
		equipment.setEndereco(institutionAddress);
		equipment.setInstituicao(institutionName);
		equipment.setDepartamento(institutionalDepartmentName);
		equipment.setFabricante(manufacturer);
		equipment.setModelo(manufacturerModelName);
		equipment.setNomemodality(modality);
		equipment.setSoftwareversion(softwareVersion);
		equipment.setStationname(stationName);

		return equipment;
	}

	public static Instancia newInstance(Date acquisitionDateTime, Date contentDateTime, Integer exposureTime,
			String imageOrientation, String imagePosition, String imageType, Integer instanceNumber, String kvp,
			String mediaStorageSopInstanceUID, String patientOrientation, Float pixelSpacing, Float sliceLocation,
			Float sliceThickness, String sopClassUID, String sopInstanceUID, String transferSyntaxUID,
			String windowCenter, String windowWidth, Integer xrayTubeCurrent) {

		Instancia instance = new Instancia();
		instance.setAcquisitiondatetime(acquisitionDateTime);
		instance.setContentdatetime(contentDateTime);
		instance.setExposuretime(exposureTime);
		instance.setImageorientation(imageOrientation);
		instance.setImageposition(imagePosition);
		instance.setImagetype(imageType);
		instance.setInstancenumber(instanceNumber);
		instance.setKvp(kvp);
		instance.setMediastoragesopinstanceuid(mediaStorageSopInstanceUID);
		instance.setPatientorientation(patientOrientation);
		instance.setPixelspacing(pixelSpacing);
		instance.setSlicelocation(sliceLocation);
		instance.setSlicethickness(sliceThickness);
		instance.setSopclassuid(sopClassUID);
		instance.setSopinstanceuid(sopInstanceUID);
		instance.setTransfersyntaxuid(transferSyntaxUID);
		instance.setWindowcenter(windowCenter);
		instance.setWindowwidth(windowWidth);
		instance.setXraytubecurrent(xrayTubeCurrent);

		return instance;
	}

	public static Tagimagem newTagimagem(String imagetype, String sopclassuid, String sopinstanceuid, String studydate,
			String seriesdate, String acquisitiondate, String contentdate, String studytime, String seriestime,
			String acquisitiontime, String contenttime, String accessionnumber, String modality,
			String presentationintenttype, String manufacturer, String institutionname, String institutionaddress,
			String referringphysiciansname, String stationname, String studydescription, String seriesdescription,
			String institutionaldepartmentname, String performingphysiciansname, String operatorsname,
			String manufacturersmodelname, String referencedpatientsequence, String anatomicregionsequence,
			String primaryAnatomicstructuresequence, String patientsname, String patientid, String softwareversions,
			String imagerpixelspacing, String positionertype, String detectortype, String detectordescription,
			String detectormode, String timeoflastdetectorcalibration, String samplesperpixel,
			String photometricinterpretation, String rows, String columns) {
		
		Tagimagem tagimagem = new Tagimagem();
		tagimagem.setImagetype(imagetype);
		tagimagem.setSopclassuid(sopclassuid);
		tagimagem.setSopinstanceuid(sopinstanceuid);
		tagimagem.setStudydate(studydate);
		tagimagem.setSeriesdate(seriesdate);
		tagimagem.setAcquisitiondate(acquisitiondate);
		tagimagem.setContentdate(contentdate);
		tagimagem.setStudytime(studytime);
		tagimagem.setSeriestime(seriestime);
		tagimagem.setAcquisitiontime(acquisitiontime);
		tagimagem.setContenttime(contenttime);
		tagimagem.setAccessionnumber(accessionnumber);
		tagimagem.setModality(modality);
		tagimagem.setPresentationintenttype(presentationintenttype);
		tagimagem.setManufacturer(manufacturersmodelname);
		tagimagem.setInstitutionname(institutionname);
		tagimagem.setInstitutionaddress(institutionaddress);
		tagimagem.setReferringphysiciansname(referringphysiciansname);
		tagimagem.setStationname(stationname);
		tagimagem.setStudydescription(studydescription);
		tagimagem.setSeriesdescription(seriesdescription);
		tagimagem.setInstitutionaldepartmentname(institutionaldepartmentname);
		tagimagem.setPerformingphysiciansname(performingphysiciansname);
		tagimagem.setOperatorsname(operatorsname);
		tagimagem.setManufacturersmodelname(manufacturersmodelname);
		tagimagem.setReferencedpatientsequence(referencedpatientsequence);
		tagimagem.setAnatomicregionsequence(anatomicregionsequence);
		tagimagem.setPrimaryAnatomicstructuresequence(primaryAnatomicstructuresequence);
		tagimagem.setPatientsname(patientsname);
		tagimagem.setPatientid(patientid);
		tagimagem.setSoftwareversions(softwareversions);
		tagimagem.setImagerpixelspacing(imagerpixelspacing);
		tagimagem.setPositionertype(positionertype);
		tagimagem.setDetectortype(detectortype);
		tagimagem.setDetectordescription(detectordescription);
		tagimagem.setDetectordescription(detectordescription);
		tagimagem.setDetectormode(detectormode);
		tagimagem.setTimeoflastdetectorcalibration(timeoflastdetectorcalibration);
		tagimagem.setSamplesperpixel(samplesperpixel);
		tagimagem.setPhotometricinterpretation(photometricinterpretation);
		tagimagem.setRows(rows);
		tagimagem.setColumns(columns);

		return tagimagem;
	}
}
