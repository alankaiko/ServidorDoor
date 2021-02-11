package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.Licenciado;
import com.laudoecia.api.repository.filtro.LicenciadoFilter;

public interface LicenciadoRepositoryQuery {
	public Page<Licenciado> Filtrando(LicenciadoFilter filtro, Pageable pageable);
}
