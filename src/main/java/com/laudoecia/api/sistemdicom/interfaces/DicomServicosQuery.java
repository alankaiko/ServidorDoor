package com.laudoecia.api.sistemdicom.interfaces;

import com.laudoecia.api.modelo.Equipamento;
import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.modelo.Series;
import com.laudoecia.api.modelo.Tagimagem;
import com.laudoecia.api.sistemdicom.LeitorDicom;

public interface DicomServicosQuery {
	public void ConstruirEntidade(LeitorDicom reader);
	Paciente ConstruirPaciente(LeitorDicom reader);
	Estudo ConstruirEstudo(LeitorDicom reader,Paciente paciente);
	Series ConstruirSerie(LeitorDicom reader, Estudo estudo);
	Equipamento ConstruirEquipamento(LeitorDicom reader, Series serie);
	Instancia ConstruirInstancia(LeitorDicom reader, Series serie);
	Tagimagem ConstruirTagImagem(LeitorDicom reader);
}
