package com.laudoecia.api.sistemdicom.worklist;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.AttributesCoercion;
import org.dcm4che3.data.Tag;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.Status;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicQueryTask;
import org.dcm4che3.net.service.DicomServiceException;

import com.laudoecia.api.sistemdicom.interfaces.ListaDeTrabalhoQuery;

public class MWLQueryTask extends BasicQueryTask {
	private final ListaDeTrabalhoQuery listatrabalho;
	private final AttributesCoercion coercao;
	private final RunInTransaction transacao;

	public MWLQueryTask(Association associacao, PresentationContext present, Attributes atrib, Attributes chaves, ListaDeTrabalhoQuery listatrabalho, AttributesCoercion coercao, RunInTransaction transacao) {
		super(associacao, present, atrib, chaves);
		this.listatrabalho = listatrabalho;
		this.coercao = coercao;
		this.transacao = transacao;
		this.setOptionalKeysNotSupported(this.listatrabalho.ChaveOpcionNaoSuportada());
	}

	@Override
	public void run() {
		this.transacao.execute(this::ExecutaRequisicao);
	}

	private void ExecutaRequisicao() {
		try {
			QueryContext contexto = this.listatrabalho.RetornaQueryContexto();
			ArchiveAEExtension arcAE = contexto.getArchiveAEExtension();
			ArchiveDeviceExtension arcdev = arcAE.getArchiveDeviceExtension();		
			this.listatrabalho.ExecutarRequisicao(arcdev.getQueryFetchSize());
			super.run();
		} catch (Exception excecao) {
			this.EscreverDimseRSP(new DicomServiceException(Status.UnableToProcess, excecao));
		} finally {
			this.listatrabalho.close();
		}
	}

	private void EscreverDimseRSP(DicomServiceException excecao) {
		int msgcodigo = rq.getInt(Tag.MessageID, -1);
		Attributes rsp = excecao.mkRSP(Dimse.C_FIND_RSP.commandField(), msgcodigo);
		try {
			as.writeDimseRSP(pc, rsp, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected boolean hasMoreMatches() throws DicomServiceException {
		try {
			return this.listatrabalho.ExisteMaisAtributos();
		} catch (Exception excecao) {
			throw new DicomServiceException(Status.UnableToProcess,"MWLQuery linha 63 " + excecao);
		}
	}

	@Override
	protected Attributes nextMatch() throws DicomServiceException {
		try {
			return this.listatrabalho.AdicionarAtributos();
		} catch (Exception excecao) {
			throw new DicomServiceException(Status.UnableToProcess,"MWLQuery linha 69 " +  excecao);
		}
	}

	@Override
	protected Attributes adjust(Attributes atributos) {
		if (atributos == null)
			return null;

		if (this.coercao != null)
			this.coercao.coerce(atributos, null);

		return this.listatrabalho.Ajustar(atributos);
	}
}
