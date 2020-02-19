package com.laudoecia.api.event;

import com.laudoecia.api.domain.Modality;

public class ModalityException extends RuntimeException {

    private String responseMessage;
    private Modality modality;

    public ModalityException(String responseMessage, Modality modality) {
        super();
        this.responseMessage = responseMessage;
        this.modality = modality;
    }

    public String getMessage() {
        return "\n Não foi possível enviar a mensagem para a modalidade \n" + modality.toString() + "\n" + responseMessage;
    }
}
