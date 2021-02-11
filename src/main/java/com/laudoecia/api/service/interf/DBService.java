package com.laudoecia.api.service.interf;

import com.laudoecia.api.modelo.Equipamento;
import com.laudoecia.api.modelo.Estudo;
import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.modelo.Serie;
import com.laudoecia.api.modelo.Tagimagem;
import com.laudoecia.api.servidor.LeitorDicom;

public interface DBService {
	public void ConstruirEntidade(LeitorDicom reader);
	Paciente ConstruirPaciente(LeitorDicom reader);
	Estudo ConstruirEstudo(LeitorDicom reader,Paciente paciente);
	Serie ConstruirSerie(LeitorDicom reader, Estudo estudo);
	Equipamento ConstruirEquipamento(LeitorDicom reader, Serie serie);
	Instancia ConstruirInstancia(LeitorDicom reader, Serie serie);
	Tagimagem ConstruirTagImagem(LeitorDicom reader);
}
