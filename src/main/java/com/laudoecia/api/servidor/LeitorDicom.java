package com.laudoecia.api.servidor;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomInputStream.IncludeBulkData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeitorDicom {
	private static final Logger LOG = LoggerFactory.getLogger(LeitorDicom.class);
	private Attributes atributo = null;
	private Attributes atributometa = null;

	public LeitorDicom() {}

	public LeitorDicom(File dicom) throws IOException {
		DicomInputStream arquivo = null;
		try {
			arquivo = new DicomInputStream(dicom);
			arquivo.setIncludeBulkData(IncludeBulkData.URI);
			this.atributo = arquivo.readDataset(-1, -1);
			this.atributometa = arquivo.readFileMetaInformation() != null ? arquivo.readFileMetaInformation()
				: atributo.createFileMetaInformation(UID.ImplicitVRLittleEndian);
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} finally {
			if (arquivo != null)
				arquivo.close();
		}
	}

	public Attributes getDataSet() {
		return this.atributo;
	}

	public Attributes getMetaData() {
		return this.atributometa;
	}

	/**************************
	 * Patient Info
	 **************************************************************************************/
	public String getPacienteCodigo() {
		return this.atributo.getString(Tag.PatientID);
	}

	public String getPacienteNome() {
		return this.atributo.getString(Tag.PatientName);
	}

	public String getPacienteSexo() {
		return this.atributo.getString(Tag.PatientSex);
	}

	public String getPacienteIdade() {
		return this.atributo.getString(Tag.PatientAge);
	}

	public Date getPacienteDataAniversario() {
		return this.atributo.getDate(Tag.PatientBirthDate);
	}
	
	public Date getDataCriacao() {
		return this.atributo.getDate(Tag.CreationDate);
	}
	
	public String getTamanho() {
		return this.atributo.getString(Tag.PatientSize);
	}
	
	public String getPeso() {
		return this.atributo.getString(Tag.PatientWeight);
	}

	public Attributes getAtributos() {
		return this.atributo;
	};
	
	public LocalDate getDataMenstrucacao() {
		return com.laudoecia.api.utils.Utils.ConverterToLocalDate(this.atributo.getDate(Tag.LastMenstrualDate));
	}

	public String getObservacoes() {
		return this.atributo.getString(Tag.ObservationDescription);
	}

	public String getPersonNome() {
		return this.atributo.getString(Tag.PersonName);
	}
	/**************************
	 * End of Patient Info
	 ***************************************/

	public String getAccessionNumber() { 
    	System.out.println("AccessionNumber" + this.atributo.getVR(Tag.AccessionNumber));
    	System.out.println("AdditionalPatientHistory" + this.atributo.getVR(Tag.AdditionalPatientHistory));
    	System.out.println("AdmittingDiagnosesDescription" + this.atributo.getVR(Tag.AdmittingDiagnosesDescription));
    	System.out.println("ReferringPhysicianName" + this.atributo.getVR(Tag.ReferringPhysicianName));
    	//System.out.println("StudyDateAndTime" + this.attr.getVR(Tag.StudyDateAndTime));
    	System.out.println("StudyID" + this.atributo.getVR(Tag.StudyID));
    	System.out.println("StudyDescription" + this.atributo.getVR(Tag.StudyDescription));
    	System.out.println("StudyInstanceUID" + this.atributo.getVR(Tag.StudyInstanceUID));
    	System.out.println("StudyPriorityID" + this.atributo.getVR(Tag.StudyPriorityID));
    	System.out.println("StudyStatusID" + this.atributo.getVR(Tag.StudyStatusID));
    	return this.atributo.getString(Tag.AccessionNumber);
    }
	
	public String getAdditionalPatientHistory() {
		return this.atributo.getString(Tag.AdditionalPatientHistory);
	}

	public String getAdmittingDiagnosesDescription() {
		return this.atributo.getString(Tag.AdmittingDiagnosesDescription);
	}

	public String getReferringPhysicianName() {
		return this.atributo.getString(Tag.ReferringPhysicianName);
	}

	public Date getStudyDateTime() {
		return this.atributo.getDate(Tag.StudyDateAndTime);
	}

	public String getStudyID() {
		return this.atributo.getString(Tag.StudyID);
	}

	public String getStudyDescription() {
		return this.atributo.getString(Tag.StudyDescription);
	}

	public String getStudyInstanceUID() {
		return this.atributo.getString(Tag.StudyInstanceUID);
	}

	public String getStudyPriorityID() {
		return this.atributo.getString(Tag.StudyPriorityID);
	}

	public String getStudyStatusID() {
		return this.atributo.getString(Tag.StudyStatusID);
	}

	/**********************************************
	 * End of Study Info
	 ***************************************************************/

	/****************************************************
	 * Series Info
	 ***************************************************************/
	public String getBodyPartExamined() {
		return this.atributo.getString(Tag.BodyPartExamined);
	}

	public String getLaterality() {
		return this.atributo.getString(Tag.Laterality);
	}

	public String getOperatorsName() {
		return this.atributo.getString(Tag.OperatorsName);
	}

	public String getPatientPosition() {
		return this.atributo.getString(Tag.PatientPosition);
	}

	public String getProtocolName() {
		return this.atributo.getString(Tag.ProtocolName);
	}

	public Date getSeriesDateTime() {
		return this.atributo.getDate(Tag.SeriesDateAndTime);
	}

	public String getSeriesDescription() {
		return this.atributo.getString(Tag.SeriesDescription);
	}

	public String getSeriesInstanceUID() {
		return this.atributo.getString(Tag.SeriesInstanceUID);
	}

	public Integer getSeriesNumber() {
		return this.atributo.getInt(Tag.SeriesNumber, 0);
	}

	/****************************************************
	 * End of Series Info
	 **********************************************************/

	/*****************************************************
	 * Equipment Info
	 *************************************************************/
	public String getConversionType() {
		return this.atributo.getString(Tag.ConversionType);
	}

	public String getDeviceSerialNumber() {
		return this.atributo.getString(Tag.DeviceSerialNumber);
	}

	public String getInstitutionAddress() {
		return this.atributo.getString(Tag.InstitutionAddress);
	}

	public String getInstitutionName() {
		return this.atributo.getString(Tag.InstitutionName);
	}

	public String getInstitutionalDepartmentName() {
		return this.atributo.getString(Tag.InstitutionalDepartmentName);
	}

	public String getManufacturer() {
		return this.atributo.getString(Tag.Manufacturer);
	}

	public String getManufacturerModelName() {
		return this.atributo.getString(Tag.ManufacturerModelName);
	}

	public String getModality() {
		return this.atributo.getString(Tag.Modality);
	}

	public String getSoftwareVersion() {
		return this.atributo.getString(Tag.SoftwareVersions);
	}

	public String getStationName() {
		return this.atributo.getString(Tag.StationName);
	}

	/*****************************************************
	 * End of Equipment Info
	 ******************************************************/

	/***********************************************************
	 * Instance Info
	 *********************************************************/
	public Date getAcquisitionDateTime() {
		return this.atributo.getDate(Tag.AcquisitionDateAndTime);
	}

	public Date getContentDateTime() {
		return this.atributo.getDate(Tag.ContentDateAndTime);
	}

	public Integer getExposureTime() {
		return this.atributo.getInt(Tag.ExposureTime, 0);
	}

	public String getImageOrientation() {
		return this.atributo.getString(Tag.ImageOrientation);
	}

	public String getImagePosition() {
		return this.atributo.getString(Tag.ImagePosition);
	}

	public String getImageType() {
		return this.atributo.getString(Tag.ImageType);
	}

	public Integer getInstanceNumber() {
		return this.atributo.getInt(Tag.InstanceNumber, 0);
	}

	public String getKvp() {
		return this.atributo.getString(Tag.KVP);
	}

	public String getMediaStorageSopInstanceUID() {
		return this.atributometa.getString(Tag.MediaStorageSOPInstanceUID);
	}// InstanceUID -> it is also the file name itself

	public String getTransferSyntaxUID() {
		return this.atributometa.getString(Tag.TransferSyntaxUID);
	}

	public String getPatientOrientation() {
		return this.atributo.getString(Tag.PatientOrientation);
	}

	public Float getPixelSpacing() {
		return this.atributo.getFloat(Tag.PixelSpacing, 0);
	}

	public Float getSliceLocation() {
		return this.atributo.getFloat(Tag.SliceLocation, 0);
	}

	public Float getSliceThickness() {
		return this.atributo.getFloat(Tag.SliceThickness, 0);
	}

	public String getSopClassUID() {
		return this.atributo.getString(Tag.SOPClassUID);
	}

	public String getSOPInstanceUID() {
		return this.atributo.getString(Tag.SOPInstanceUID);
	}

	public String getWindowCenter() {
		return this.atributo.getString(Tag.WindowCenter);
	}

	public String getWindowWidth() {
		return this.atributo.getString(Tag.WindowWidth);
	}

	public Integer getXrayTubeCurrent() {
		return this.atributo.getInt(Tag.XRayTubeCurrent, 0);
	}

	/***************************************************
	 * Instance Info
	 ***********************************************************************/

	/**************************
	 * TagImagem Info
	 **************************************************************************************/
	public String getimagetype() {
		return this.atributo.getString(Tag.ImageType);
	}

	public String getsopclassuid() {
		return this.atributo.getString(Tag.SOPClassUID);
	}

	public String getsopinstanceuid() {
		return this.atributo.getString(Tag.SOPInstanceUID);
	}

	public String getstudydate() {
		return this.atributo.getString(Tag.StudyDate);
	}

	public String getseriesdate() {
		return this.atributo.getString(Tag.SeriesDate);
	}

	public String getacquisitiondate() {
		return this.atributo.getString(Tag.AcquisitionDate);
	}

	public String getcontentdate() {
		return this.atributo.getString(Tag.ContentDate);
	}

	public String getstudytime() {
		return this.atributo.getString(Tag.SeriesTime);
	}

	public String getseriestime() {
		return this.atributo.getString(Tag.AcquisitionTime);
	}

	public String getacquisitiontime() {
		return this.atributo.getString(Tag.AcquisitionTime);
	}

	public String getcontenttime() {
		return this.atributo.getString(Tag.ContentTime);
	}

	public String getaccessionnumber() {
		return this.atributo.getString(Tag.AccessionNumber);
	}

	public String getmodality() {
		return this.atributo.getString(Tag.Modality);
	}

	public String getpresentationintenttype() {
		return this.atributo.getString(Tag.PresentationIntentType);
	}

	public String getmanufacturer() {
		return this.atributo.getString(Tag.Manufacturer);
	}

	public String getinstitutionname() {
		return this.atributo.getString(Tag.InstitutionName);
	}

	public String getinstitutionaddress() {
		return this.atributo.getString(Tag.InstitutionAddress);
	}

	public String getreferringphysiciansname() {
		return this.atributo.getString(Tag.ReferringPhysicianName);
	}

	public String getstationname() {
		return this.atributo.getString(Tag.StationName);
	}

	public String getstudydescription() {
		return this.atributo.getString(Tag.StudyDescription);
	}

	public String getseriesdescription() {
		return this.atributo.getString(Tag.SeriesDescription);
	}

	public String getinstitutionaldepartmentname() {
		return this.atributo.getString(Tag.InstitutionalDepartmentName);
	}

	public String getperformingphysiciansname() {
		return this.atributo.getString(Tag.PerformingPhysicianName);
	}

	public String getoperatorsname() {
		return this.atributo.getString(Tag.OperatorsName);
	}

	public String getmanufacturersmodelname() {
		return this.atributo.getString(Tag.ManufacturerModelName);
	}

	public String getreferencedpatientsequence() {
		return this.atributo.getString(Tag.ReferencedPatientSequence);
	}

	public String getanatomicregionsequence() {
		return this.atributo.getString(Tag.AnatomicRegionSequence);
	}

	public String getprimaryAnatomicstructuresequence() {
		return this.atributo.getString(Tag.PrimaryAnatomicStructureSequence);
	}

	public String getpatientsname() {
		return this.atributo.getString(Tag.PatientName);
	}

	public String getpatientid() {
		return this.atributo.getString(Tag.PatientID);
	}

	public String getpatientsbirthdate() {
		return this.atributo.getString(Tag.PatientBirthDate);
	}

	public String getsoftwareversions() {
		return this.atributo.getString(Tag.SoftwareVersions);
	}

	public String getimagerpixelspacing() {
		return this.atributo.getString(Tag.ImagerPixelSpacing);
	}

	public String getpositionertype() {
		return this.atributo.getString(Tag.PositionerType);
	}

	public String getdetectortype() {
		return this.atributo.getString(Tag.DetectorType);
	}

	public String getdetectordescription() {
		return this.atributo.getString(Tag.DetectorDescription);
	}

	public String getdetectormode() {
		return this.atributo.getString(Tag.DetectorMode);
	}

	public String gettimeoflastdetectorcalibration() {
		return this.atributo.getString(Tag.TimeOfLastDetectorCalibration);
	}

	public String getsamplesperpixel() {
		return this.atributo.getString(Tag.SamplesPerPixel);
	}

	public String getphotometricinterpretation() {
		return this.atributo.getString(Tag.PhotometricInterpretation);
	}

	public String getrows() {
		return this.atributo.getString(Tag.Rows);
	}

	public String getcolumns() {
		return this.atributo.getString(Tag.Columns);
	}

	/**************************
	 * End of TagImagem Info
	 ***************************************/

	@Override
	public String toString() {

		return String.format(
				"[Patient] => Patient ID: %s, Patient Name: %s, Patient Sex: %s, Patient Age: %s, Patient Birthday: %s \n"
						+ "----------------------------------------------------------------------------------------------------------------------\n"
						+ "[Study] => Accession Number: %s, Additional Patient History: %s, Admitting Diagnoses Description: %s, Referring Physician Name: %s, SOP Instance UID: %s, Study Date Time: %s, Study ID: %s, Study Instance UID: %s, Study Priority ID: %s, Study Status ID: %s \n"
						+ "----------------------------------------------------------------------------------------------------------------------\n"
						+ "[Series] => Body Part Examined: %s, Laterality: %s, Operators Name: %s, Patient Position: %s, Protocol Name: %s, Series Date Time: %s, Series Description: %s, Series Instance UID: %s, Series Number: %d \n"
						+ "----------------------------------------------------------------------------------------------------------------------\n"
						+ "[Equipment] => Conversion Type: %s, Institutional Department Name: %s, Device SerialNumber: %s, Instituition Address: %s, Institution Name: %s, Manufacturer: %s, Manufacturer Model Name: %s, Modality: %s, Software Version: %s, Station Name: %s\n"
						+ "----------------------------------------------------------------------------------------------------------------------\n"
						+ "[Instance] --> Acquisition Date Time: %s, Content Date Time: %s, Exposure Time: %s, Image Orientation: %s, Image Position: %s, Image Type: %s, Instance Number: %d, kvp: %s,  Media Storage SOP InstanceUID: %s, TransferSyntax UID: %s, Patient Orientation:  %s, Pixel Spacing: %f, Slice Location: %f, Slice Thickness: %f, SOP Class UID: %s, SOP Instance UID: %s, window Center: %s, window Width: %s, Xray Tube Current: %d \n",
				getPacienteCodigo(), getPacienteNome(), getPacienteSexo(), getPacienteIdade(), getPacienteDataAniversario(),
				getAccessionNumber(), getAdditionalPatientHistory(), getAdmittingDiagnosesDescription(),
				getReferringPhysicianName(), getSOPInstanceUID(), getStudyDateTime(), getStudyID(),
				getStudyInstanceUID(), getStudyPriorityID(), getStudyStatusID(), getBodyPartExamined(), getLaterality(),
				getOperatorsName(), getPatientPosition(), getProtocolName(), getSeriesDateTime(),
				getSeriesDescription(), getSeriesInstanceUID(), getSeriesNumber(), getConversionType(),
				getInstitutionalDepartmentName(), getDeviceSerialNumber(), getInstitutionAddress(),
				getInstitutionName(), getManufacturer(), getManufacturerModelName(), getModality(),
				getSoftwareVersion(), getStationName(), getAcquisitionDateTime(), getContentDateTime(),
				getExposureTime(), getImageOrientation(), getImagePosition(), getImageType(), getInstanceNumber(),
				getKvp(), getMediaStorageSopInstanceUID(), getTransferSyntaxUID(), getPatientOrientation(),
				getPixelSpacing(), getSliceLocation(), getSliceThickness(), getSopClassUID(), getSOPInstanceUID(),
				getWindowCenter(), getWindowWidth(), getXrayTubeCurrent());
	}

}
