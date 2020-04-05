package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Licenciado;
import com.laudoecia.api.repository.filtro.LicenciadoFilter;

public interface LicenciadoRepositoryQuery {
	public Page<Licenciado> Filtrando(LicenciadoFilter filtro, Pageable pageable);
}
