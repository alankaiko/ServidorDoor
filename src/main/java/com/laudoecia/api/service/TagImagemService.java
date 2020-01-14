package com.laudoecia.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Tagimagem;
import com.laudoecia.api.repository.TagImagemRepository;
import com.laudoecia.api.repository.resumo.TagImagemGamb;

@Service
public class TagImagemService {
	@Autowired
	private TagImagemRepository dao;
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);

	
	public List<Tagimagem> Listar() {
		return this.dao.findAll();
	}

	public Tagimagem Criar(Tagimagem tagimagem) {
		try {
			return this.dao.save(tagimagem);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de TagImagemService");
			e.printStackTrace();
			return null;
		}		
	}

	public Tagimagem BuscarPorId(Long id) {
		Optional<Tagimagem> tagimagem = this.dao.findById(id);

		if (tagimagem.get() == null)
			throw new EmptyResultDataAccessException(1);

		return tagimagem.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de TagImagemService");
			e.printStackTrace();
		}
	}

	public void Deletar(Tagimagem tagimagem) {
		try {
			this.dao.delete(tagimagem);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de TagImagemService");
			e.printStackTrace();
		}
	}

	public Tagimagem Atualizar(Long id, Tagimagem tagimagem) {
		try {
			Tagimagem salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(tagimagem, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de TagImagemService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de TagImagemService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<TagImagemGamb> CriarTabela(Long codigo){
		Tagimagem tagimagem = this.BuscarPorId(codigo);
		List<TagImagemGamb> lista = new ArrayList<TagImagemGamb>();
		
		TagImagemGamb gamb = new TagImagemGamb();
		gamb.setTag("(0008,0010)");
		gamb.setIdentificacao("ImageType");
		gamb.setCampo(tagimagem.getImagetype());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0016)");
		gamb.setIdentificacao("SOPClassUID");
		gamb.setCampo(tagimagem.getSopclassuid());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0018)");
		gamb.setIdentificacao("SOPInstanceUID");
		gamb.setCampo(tagimagem.getSopinstanceuid());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0020)");
		gamb.setIdentificacao("StudyDate");
		gamb.setCampo(tagimagem.getStudydate());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0021)");
		gamb.setIdentificacao("SeriesDate");
		gamb.setCampo(tagimagem.getSeriesdate());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0022)");
		gamb.setIdentificacao("AcquisitionDate");
		gamb.setCampo(tagimagem.getAcquisitiondate());
		lista.add(gamb);
	   
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0023)");
		gamb.setIdentificacao("ContentDate");
		gamb.setCampo(tagimagem.getContentdate());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0031)");
		gamb.setIdentificacao("SeriesTime");
		gamb.setCampo(tagimagem.getSeriestime());
		lista.add(gamb);
	 
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0032)");
		gamb.setIdentificacao("AcquisitionTime");
		gamb.setCampo(tagimagem.getAcquisitiontime());
		lista.add(gamb);
	   
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0033)");
		gamb.setIdentificacao("ContentTime");
		gamb.setCampo(tagimagem.getContenttime());
		lista.add(gamb);
	   
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0050)");
		gamb.setIdentificacao("AccessionNumber");
		gamb.setCampo(tagimagem.getAccessionnumber());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0060)");
		gamb.setIdentificacao("Modality");
		gamb.setCampo(tagimagem.getModality());
		lista.add(gamb);
	  
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0068)");
		gamb.setIdentificacao("PresentationIntentType");
		gamb.setCampo(tagimagem.getPresentationintenttype());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0070)");
		gamb.setIdentificacao("Manufacturer");
		gamb.setCampo(tagimagem.getManufacturer());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0080)");
		gamb.setIdentificacao("InstitutionName");
		gamb.setCampo(tagimagem.getInstitutionname());
		lista.add(gamb);
	    
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0081)");
		gamb.setIdentificacao("InstitutionAddress");
		gamb.setCampo(tagimagem.getInstitutionaddress());
		lista.add(gamb);
	    
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,0090)");
		gamb.setIdentificacao("ReferringPhysicianName");
		gamb.setCampo(tagimagem.getReferringphysiciansname());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1010)");
		gamb.setIdentificacao("StationName");
		gamb.setCampo(tagimagem.getStationname());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1030)");
		gamb.setIdentificacao("StudyDescription");
		gamb.setCampo(tagimagem.getStudydescription());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0008,103E)");
		gamb.setIdentificacao("SeriesDescription");
		gamb.setCampo(tagimagem.getSeriesdescription());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1040)");
		gamb.setIdentificacao("InstitutionalDepartmentName");
		gamb.setCampo(tagimagem.getInstitutionaldepartmentname());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1040)");
		gamb.setIdentificacao("InstitutionalDepartmentName");
		gamb.setCampo(tagimagem.getInstitutionaldepartmentname());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1050)");
		gamb.setIdentificacao("PerformingPhysicianName");
		gamb.setCampo(tagimagem.getPerformingphysiciansname());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1070)");
		gamb.setIdentificacao("OperatorsName");
		gamb.setCampo(tagimagem.getOperatorsname());
		lista.add(gamb);
	   
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1090)");
		gamb.setIdentificacao("ManufacturerModelName");
		gamb.setCampo(tagimagem.getManufacturersmodelname());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,1120)");
		gamb.setIdentificacao("ReferencedPatientSequence");
		gamb.setCampo(tagimagem.getReferencedpatientsequence());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0008,2218)");
		gamb.setIdentificacao("AnatomicRegionSequence");
		gamb.setCampo(tagimagem.getAnatomicregionsequence());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0008,2228)");
		gamb.setIdentificacao("PrimaryAnatomicStructureSequence");
		gamb.setCampo(tagimagem.getPrimaryAnatomicstructuresequence());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0010,0010)");
		gamb.setIdentificacao("PatientName");
		gamb.setCampo(tagimagem.getPatientsname());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0010,0020)");
		gamb.setIdentificacao("PatientID");
		gamb.setCampo(tagimagem.getPatientid());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0018,1020)");
		gamb.setIdentificacao("SoftwareVersions");
		gamb.setCampo(tagimagem.getSoftwareversions());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0018,1164)");
		gamb.setIdentificacao("ImagerPixelSpacing");
		gamb.setCampo(tagimagem.getImagerpixelspacing());
		lista.add(gamb);
	
		gamb = new TagImagemGamb();
		gamb.setTag("(0018,1508)");
		gamb.setIdentificacao("PositionerType");
		gamb.setCampo(tagimagem.getPositionertype());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0018,7004)");
		gamb.setIdentificacao("DetectorType");
		gamb.setCampo(tagimagem.getDetectortype());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0018,7006)");
		gamb.setIdentificacao("DetectorDescription");
		gamb.setCampo(tagimagem.getDetectordescription());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0018,7008)");
		gamb.setIdentificacao("DetectorMode");
		gamb.setCampo(tagimagem.getDetectormode());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0018,700E)");
		gamb.setIdentificacao("TimeOfLastDetectorCalibration");
		gamb.setCampo(tagimagem.getTimeoflastdetectorcalibration());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0028,0002)");
		gamb.setIdentificacao("SamplesPerPixel");
		gamb.setCampo(tagimagem.getSamplesperpixel());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0028,0004)");
		gamb.setIdentificacao("PhotometricInterpretation");
		gamb.setCampo(tagimagem.getPhotometricinterpretation());
		lista.add(gamb);
		
		gamb = new TagImagemGamb();
		gamb.setTag("(0028,0010)");
		gamb.setIdentificacao("Rows");
		gamb.setCampo(tagimagem.getRows());
		lista.add(gamb);

		gamb = new TagImagemGamb();
		gamb.setTag("(0028,0011)");
		gamb.setIdentificacao("Columns");
		gamb.setCampo(tagimagem.getColumns());
		lista.add(gamb);
		
		System.out.println("vindo aqui");
		return lista;
	}

	
}
