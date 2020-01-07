package com.laudoecia.api.repository.impl;

import java.util.List;

import com.laudoecia.api.domain.Study;

public interface StudyRepositoryQuery {
	public List<Study> ListarMaximoCom(int primeiro, int maximo);
}
