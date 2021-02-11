package com.laudoecia.api.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laudoecia.api.gerenciarentrada.ReplicarDicom;
import com.laudoecia.api.modelo.Equipamento;
import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.modelo.Serie;
import com.laudoecia.api.modelo.Tagimagem;
import com.laudoecia.api.service.interf.DBService;
import com.laudoecia.api.servidor.LeitorDicom;
import com.laudoecia.api.utils.DicomEntityBuilder;
import com.laudoecia.api.utils.Utils;

@Service
public class DBServiceImpl implements DBService{

	private static final Logger LOG = LoggerFactory.getLogger(DBServiceImpl.class);

	@Autowired
	private InstanciaService serviceinstancia;

	@Autowired
	private SerieService serviceserie;

	@Autowired
	private EstudoService serviceestudo;

	@Autowired
	private PacienteService servicepaciente;

	@Autowired
	private TagImagemService TagImagemService;

	@Autowired
	private EquipamentoService serviceesquipamento;

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private ReplicarDicom reposter;

	@Transactional
	@Override
	public Paciente ConstruirPaciente(LeitorDicom reader) {
		LOG.info("Processando; Paciente: {}, Código: {}", reader.getPacienteNome(), reader.getPacienteCodigo());
		Paciente paciente = this.servicepaciente.BuscarPorPacienteId(reader.getPacienteCodigo());

		if (paciente == null) {
			paciente = DicomEntityBuilder.NovoPaciente(reader.getPacienteIdade(), reader.getPacienteDataAniversario(),
				reader.getPacienteCodigo(), reader.getPacienteNome(), reader.getPacienteSexo(), reader.getDataCriacao(),
				reader.getTamanho(), reader.getPeso());

			this.servicepaciente.Criar(paciente);
			paciente = this.servicepaciente.BuscarPorPacienteId(reader.getPacienteCodigo());
		} else {
			LOG.info("Paciente já existe; Nome: {}, Código: {} ", reader.getPacienteNome(), reader.getPacienteCodigo());
		}
	
		return paciente;
	}

	@Transactional
	@Override
	public Estudo ConstruirEstudo(LeitorDicom reader, Paciente paciente) {
		Estudo estudo = this.serviceestudo.BuscarPorStudyInstanceuid(reader.getStudyInstanceUID());

		if (estudo == null) {
			estudo = DicomEntityBuilder.NovoEstudo(reader.getAccessionNumber(), reader.getAdditionalPatientHistory(),
				reader.getAdmittingDiagnosesDescription(), reader.getReferringPhysicianName(),
				reader.getSeriesDateTime(), reader.getStudyID(), reader.getStudyDescription(),
				reader.getStudyInstanceUID(), reader.getStudyPriorityID(), reader.getStudyStatusID());
			estudo.setPaciente(paciente);

			this.serviceestudo.Criar(estudo);
			estudo = this.serviceestudo.BuscarPorStudyInstanceuid(reader.getStudyInstanceUID());
		} else {
			LOG.info("Estudo já existe; Estudo instance UID: {}", estudo.getStudyinstanceuid());
		}

		return estudo;
	}

	@Transactional
	@Override
	public Serie ConstruirSerie(LeitorDicom reader, Estudo estudo) {
		Serie serie = this.serviceserie.BuscaPorInstanceENumber(reader.getSeriesInstanceUID(), reader.getSeriesNumber());

		if (serie == null) {
			serie = DicomEntityBuilder.NovaSerie(reader.getBodyPartExamined(), reader.getLaterality(),
				reader.getOperatorsName(), reader.getPatientPosition(), reader.getProtocolName(),
				reader.getSeriesDateTime(), reader.getSeriesDescription(), reader.getSeriesInstanceUID(),
				reader.getSeriesNumber());

			serie.setStudy(estudo);
			this.serviceserie.Criar(serie);
			serie = this.serviceserie.BuscaPorInstanceENumber(reader.getSeriesInstanceUID(), reader.getSeriesNumber());
		} else {
			LOG.info("Serie já existe; Serie Instance UID: {}", serie.getSeriesinstanceuid());
		}

		return serie;
	}

	@Transactional
	@Override
	public Equipamento ConstruirEquipamento(LeitorDicom reader, Serie serie) {
		Equipamento equipmento = this.serviceesquipamento.BuscarPorSerieEquipamento(serie.getCodigo());

		if (equipmento == null) {
			equipmento = DicomEntityBuilder.NovoEquipamento(reader.getConversionType(), reader.getDeviceSerialNumber(),
					reader.getInstitutionAddress(), reader.getInstitutionName(),
					reader.getInstitutionalDepartmentName(), reader.getManufacturer(),
					reader.getManufacturerModelName(), reader.getModality(), reader.getSoftwareVersion(),
					reader.getStationName());

			equipmento.setSeries(serie);
			this.serviceesquipamento.Criar(equipmento);
			equipmento = this.serviceesquipamento.BuscarPorSerieEquipamento(serie.getCodigo());

		} else {
			LOG.info("Equipamento já existe; Código {}", equipmento.getCodigo());
		}

		return equipmento;
	}

	@Transactional
	@Override
	public Instancia ConstruirInstancia(LeitorDicom reader, Serie serie) {
		Instancia instancia = this.serviceinstancia.BuscarPorInstanciaUid(reader.getSOPInstanceUID());

		if (instancia == null) {
			instancia = DicomEntityBuilder.newInstance(reader.getAcquisitionDateTime(), reader.getContentDateTime(),
				reader.getExposureTime(), reader.getImageOrientation(), reader.getImagePosition(),
				reader.getImageType(), reader.getInstanceNumber(), reader.getKvp(),
				reader.getMediaStorageSopInstanceUID(), reader.getPatientOrientation(), reader.getPixelSpacing(),
				reader.getSliceLocation(), reader.getSliceThickness(), reader.getSopClassUID(),
				reader.getSOPInstanceUID(), reader.getTransferSyntaxUID(), reader.getWindowCenter(),
				reader.getWindowWidth(), reader.getXrayTubeCurrent());

			instancia.setSeries(serie);
			instancia.setTagimagem(this.ConstruirTagImagem(reader));
			this.serviceinstancia.Criar(instancia);
			instancia = this.serviceinstancia.BuscarPorInstanciaUid(reader.getSOPInstanceUID());

		} else {
			LOG.info("Instancia já existe; InstanceUOD {}, Instance Number {}", instancia.getInstancenumber(), instancia.getInstancenumber());
		}

		return instancia;
	}

	@Transactional
	@Override
	public void ConstruirEntidade(LeitorDicom reader) {

		try {
			LOG.info("====================================================================================================");
			printStats(reader.getPacienteNome() + " " + reader.getPacienteCodigo() + " " 
					+ reader.getPacienteIdade() + " " + reader.getPacienteSexo() + " Started");
			Paciente paciente = ConstruirPaciente(reader);
			reposter.Adicionar(reader.getMediaStorageSopInstanceUID(), paciente.toString());

			if (paciente != null) {
				Estudo study = ConstruirEstudo(reader, paciente);
				
				if (study != null) {
					Serie series = ConstruirSerie(reader, study);
					if (series != null) {
						Equipamento equipment = ConstruirEquipamento(reader, series);
						Instancia instance = ConstruirInstancia(reader, series);

						series.setDatamodicifacao(instance.getDatacriacao());
						this.serviceserie.Criar(series);

						equipment.setDatamodificacao(instance.getDatacriacao());
						this.serviceesquipamento.Criar(equipment);

						study.setDatamodificacao(instance.getDatacriacao());
						this.serviceestudo.Criar(study);

						paciente.setDatamodificacao(Utils.ConverterToLocalDate(instance.getDatacriacao()));
						this.servicepaciente.Criar(paciente);

						LOG.info("Dicom Instancia salvo! {}", instance.toString());
					}
				}
			}

			reposter.Remover(reader.getMediaStorageSopInstanceUID());

			printStats(reader.getPacienteNome() + " " + reader.getPacienteCodigo() + " " + reader.getPacienteIdade() + " "+ reader.getPacienteSexo() + " Ended");
			LOG.info("=================================================================================================================================");
			LOG.info("");

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	public void printStats(String status) {
		LOG.info("{} {} {} [Active Threads: {}] ", Thread.currentThread().getId(), Thread.currentThread().getName(), status, Thread.activeCount());

	}

	@Transactional
	@Override
	public Tagimagem ConstruirTagImagem(LeitorDicom reader) {
		
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
