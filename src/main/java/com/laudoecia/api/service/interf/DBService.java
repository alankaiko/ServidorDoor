package com.laudoecia.api.service.interf;

import com.laudoecia.api.domain.Modality;
import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.domain.Paciente;
import com.laudoecia.api.domain.Series;
import com.laudoecia.api.domain.Study;
import com.laudoecia.api.domain.Tagimagem;
import com.laudoecia.api.server.DicomReader;

public interface DBService {

	public void buildEntities(DicomReader reader);
	Paciente buildPatient(DicomReader reader);
	Study buildStudy(DicomReader reader,Paciente patient);
	Series buildSeries(DicomReader reader, Study study);
	Modality buildEquipment(DicomReader reader, Series series);
	Instance buildInstance(DicomReader reader, Series series);
	Tagimagem buildTagImagem(DicomReader reader);
}
