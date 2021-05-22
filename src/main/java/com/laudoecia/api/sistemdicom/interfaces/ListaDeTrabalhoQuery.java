package com.laudoecia.api.sistemdicom.interfaces;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.service.DicomServiceException;

import com.laudoecia.api.modelo.Atendimento;
import com.laudoecia.api.sistemdicom.worklist.QueryContext;

public interface ListaDeTrabalhoQuery {
	public Attributes CriarAtributos(Atendimento atendimento);
    public void ExecutarRequisicao(int tamanho);
    public void ExecutarRequisicao(int tamanho, int desl, int limite);
    public boolean ExisteMaisAtributos() throws DicomServiceException;
    public Attributes AdicionarAtributos();
    public Attributes Ajustar(Attributes atributos);
	public boolean ChaveOpcionNaoSuportada();
    public QueryContext RetornaQueryContexto() ;    
    public void close();
}
