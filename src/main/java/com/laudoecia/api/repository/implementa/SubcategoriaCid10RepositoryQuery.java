package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.SubcategoriaCid10;
import com.laudoecia.api.repository.filtro.SubcategoriaCid10Filter;
import com.laudoecia.api.repository.resumo.ResumoSubcategoriaCid10;

public interface SubcategoriaCid10RepositoryQuery {
	public Page<SubcategoriaCid10> Filtrando(SubcategoriaCid10Filter filtro, Pageable pageable);
	public Page<ResumoSubcategoriaCid10> Resumir(SubcategoriaCid10Filter filtro, Pageable pageable);
	public Long BuscarIdMax();
}
