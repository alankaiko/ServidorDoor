package com.laudoecia.api.repository.filtro;

public class PdfFiltroDados {
	private String executante;
	private String procedimento;
	private String codigoprocedimento;

	public String getExecutante() {
		return executante;
	}

	public void setExecutante(String executante) {
		this.executante = executante;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getCodigoprocedimento() {
		return codigoprocedimento;
	}
	
	public void setCodigoprocedimento(String codigoprocedimento) {
		this.codigoprocedimento = codigoprocedimento;
	}	
}
