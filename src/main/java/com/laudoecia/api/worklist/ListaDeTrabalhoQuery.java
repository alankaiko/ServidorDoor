package com.laudoecia.api.worklist;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.service.DicomServiceException;

import com.laudoecia.api.modelo.MWLItem;

public interface ListaDeTrabalhoQuery {
	public Attributes CriarAtributos(MWLItem results);
    public void ExecutarRequisicao(int tamanho);
    public void ExecutarRequisicao(int tamanho, int desl, int limite);
    public boolean ExisteMaisAtributos() throws DicomServiceException;
    public Attributes AdicionarAtributos();
    public Attributes Ajustar(Attributes atributos);
	public boolean ChaveOpcionNaoSuportada();
    public QueryContext RetornaQueryContexto() ;    
    public void close();
}
