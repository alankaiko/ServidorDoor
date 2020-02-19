package com.laudoecia.api.teste;

import com.laudoecia.api.domain.Modality;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.parser.Parser;

public class HapiSendMessageSimpleExample {

	//private static final int PORT_NUMBER = 9081;// change this to whatever your port number is
	private Modality modalidade;

	// In HAPI, almost all things revolve around a context object
	private static HapiContext context = new DefaultHapiContext();
	
	
	public void ExecutarMensagem() {
		try {

			ADT_A01 adtMessage = (ADT_A01) AdtMessageFactory.createMessage("A01");

			// create a new MLLP client over the specified port
			Connection connection = context.newClient(this.modalidade.getIp(), this.modalidade.getPort(), false);

			// The initiator which will be used to transmit our message
			Initiator initiator = connection.getInitiator();

			// send the previously created HL7 message over the connection established
			Parser parser = context.getPipeParser();
			System.out.println("Sending message:" + "\n" + parser.encode(adtMessage));
			Message response = initiator.sendAndReceive(adtMessage);

			// display the message response received from the remote party
			String responseString = parser.encode(response);
			System.out.println("Received response:\n" + responseString);
			System.out.println("recebeu");

		} catch (Exception e) {
			System.out.println("ERRO-------------------------------------------ERRO");
			e.printStackTrace();
		}
	}
	

	public Modality getModalidade() {
		return modalidade;
	}
	
	public void setModalidade(Modality modalidade) {
		this.modalidade = modalidade;
	}
}
