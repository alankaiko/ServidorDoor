package com.laudoecia.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laudoecia.api.domain.Modality;
import com.laudoecia.api.event.ModalityException;
import com.laudoecia.api.repository.ModalityRepository;
import com.laudoecia.api.repository.OrderTypeRepository;
import com.laudoecia.api.repository.filtro.ModalityFilter;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.AbstractMessage;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.ORR_O02;
import ca.uhn.hl7v2.parser.PipeParser;

@Service
public class ModalityService {

	@Autowired
	private OrderTypeRepository orderTypeRepository;
	
	@Autowired
	private ModalityRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(StudyService.class);
	
	public List<Modality> Listar() {
		return this.dao.findAll();
	}

	public Modality Criar(Modality modality) {
		try {
			return this.dao.save(modality);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ModalityService");
			e.printStackTrace();
			return null;
		}		
	}

	public Modality BuscarPorId(Long id) {
		Optional<Modality> abreviatura = this.dao.findById(id);

		if (abreviatura.get() == null)
			throw new EmptyResultDataAccessException(1);

		return abreviatura.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModalityService");
			e.printStackTrace();
		}
	}

	public void Deletar(Modality modality) {
		try {
			this.dao.delete(modality);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModalityService");
			e.printStackTrace();
		}
	}

	public Modality Atualizar(Long id, Modality modality) {
		try {
			Modality salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(modality, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ModalityService");
			e.printStackTrace();
			return null;
		}		
	}


	public Page<Modality> Filtrando(ModalityFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de ModalityService");
			e.printStackTrace();
			return null;
		}	
	}

	
	
	
	
	
	

	public String sendMessage(AbstractMessage message, String orderType) throws HL7Exception, LLPException, IOException {
		Modality modality = orderTypeRepository.getByName(orderType).getModality();
		Message response = post(modality, message);
		String responseMessage = parseResponse(response);
		if (response instanceof ORR_O02) {
			ORR_O02 acknowledgement = (ORR_O02) response;
			String acknowledgmentCode = acknowledgement.getMSA().getAcknowledgmentCode().getValue();
			processAcknowledgement(modality, responseMessage, acknowledgmentCode);
		} else if (response instanceof ACK) {
			ACK acknowledgement = (ACK) response;
			String acknowledgmentCode = acknowledgement.getMSA().getAcknowledgmentCode().getValue();
			processAcknowledgement(modality, responseMessage, acknowledgmentCode);
		} else {
			throw new ModalityException(responseMessage, modality);
		}
		return responseMessage;
	}

	Message post(Modality modality, Message requestMessage) throws LLPException, IOException, HL7Exception {
		Connection newClientConnection = null;
		try {
			HapiContext hapiContext = new DefaultHapiContext();
			newClientConnection = hapiContext.newClient(modality.getIp(), modality.getPort(), false);
			Initiator initiator = newClientConnection.getInitiator();
			return initiator.sendAndReceive(requestMessage);
		} finally {
			if (newClientConnection != null) {
				newClientConnection.close();
			}
		}
	}

	String parseResponse(Message response) throws HL7Exception {
		return new PipeParser().encode(response);
	}

	private void processAcknowledgement(Modality modality, String responseMessage, String acknowledgmentCode) {
		if (!AcknowledgmentCode.AA.toString().equals(acknowledgmentCode)) {
			throw new ModalityException(responseMessage, modality);
		}
	}
}
