package com.laudoecia.api.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.laudoecia.api.domain.CapituloCID10;
import com.laudoecia.api.domain.CapituloCID10_;
import com.laudoecia.api.domain.CategoriaCID10;
import com.laudoecia.api.domain.CategoriaCID10_;
import com.laudoecia.api.domain.GrupoCID10;
import com.laudoecia.api.domain.GrupoCID10_;
import com.laudoecia.api.domain.SubcategoriaCid10;
import com.laudoecia.api.domain.SubcategoriaCid10_;
import com.laudoecia.api.repository.filtro.SubcategoriaCid10Filter;

public class SubcategoriaCid10RepositoryImpl implements SubcategoriaCid10RepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<SubcategoriaCid10> Filtrando(SubcategoriaCid10Filter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<SubcategoriaCid10> query = builder.createQuery(SubcategoriaCid10.class);
		Root<SubcategoriaCid10> root = query.from(SubcategoriaCid10.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<SubcategoriaCid10> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, SubcategoriaCid10Filter filtro, Root<SubcategoriaCid10> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		CriteriaQuery<SubcategoriaCid10> criteria = builder.createQuery(SubcategoriaCid10.class);
		Root<CategoriaCID10> rootcategoria = criteria.from(CategoriaCID10.class);
		Root<GrupoCID10> rootgrupo = criteria.from(GrupoCID10.class);
		Root<CapituloCID10> rootcapitulo = criteria.from(CapituloCID10.class);
		
		if(!StringUtils.isEmpty(filtro.getCodigotexto()))
			lista.add(builder.like(builder.lower(root.get(SubcategoriaCid10_.codigotexto)), "%"+ filtro.getCodigotexto().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.lower(root.get(SubcategoriaCid10_.nome)), "%"+ filtro.getNome().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNomecategoria()))
			lista.add(builder.like(builder.lower(rootcategoria.get(CategoriaCID10_.nome)), "%"+ filtro.getNomecategoria().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNomegrupo()))
			lista.add(builder.like(builder.lower(rootgrupo.get(GrupoCID10_.nome)), "%"+ filtro.getNomegrupo().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNomecapitulo()))
			lista.add(builder.like(builder.lower(rootcapitulo.get(CapituloCID10_.nome)), "%"+ filtro.getNomecapitulo().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(SubcategoriaCid10Filter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<SubcategoriaCid10> root = query.from(SubcategoriaCid10.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}
