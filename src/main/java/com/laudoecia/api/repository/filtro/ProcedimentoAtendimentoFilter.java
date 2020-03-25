package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

public class ProcedimentoAtendimentoFilter {
	private LocalDate preventregalaudo;
	private LocalDate dataexecucao;

	public LocalDate getPreventregalaudo() {
		return preventregalaudo;
	}

	public void setPreventregalaudo(LocalDate preventregalaudo) {
		this.preventregalaudo = preventregalaudo;
	}

	public LocalDate getDataexecucao() {
		return dataexecucao;
	}

	public void setDataexecucao(LocalDate dataexecucao) {
		this.dataexecucao = dataexecucao;
	}
}
