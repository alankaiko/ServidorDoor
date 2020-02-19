package com.laudoecia.api.teste;

import java.io.IOException;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

public class AdtMessageFactory {

	public static Message createMessage(String messageType) throws HL7Exception, IOException {

		// This patterns enables you to build other message types
		if (messageType.equals("A01")) {
			return new OurAdtA01MessageBuilder().Build();
		}

		// if other types of ADT messages are needed, then implement your builders here
		throw new RuntimeException(
				String.format("%s Mensagem enviada nao e suportada ainda.", messageType));

	}
}