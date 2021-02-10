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

import com.laudoecia.api.domain.ModeloDeLaudoDoProc;
import com.laudoecia.api.domain.ModeloDeLaudoDoProc_;
import com.laudoecia.api.repository.filtro.ModeloDeLaudoDoProcFilter;

public class ModeloDeLaudoDoProcRepositoryImpl implements ModeloDeLaudoDoProcRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<ModeloDeLaudoDoProc> Filtrando(ModeloDeLaudoDoProcFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ModeloDeLaudoDoProc> query = builder.createQuery(ModeloDeLaudoDoProc.class);
		Root<ModeloDeLaudoDoProc> root = query.from(ModeloDeLaudoDoProc.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<ModeloDeLaudoDoProc> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, ModeloDeLaudoDoProcFilter filtro, Root<ModeloDeLaudoDoProc> root) {
		List<Predicate> lista= new ArrayList<Predicate>();

		if(!StringUtils.isEmpty(filtro.getDescricao()))
			lista.add(builder.like(builder.lower(root.get(ModeloDeLaudoDoProc_.descricao)), "%"+ filtro.getDescricao().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getCustomstring()))
			lista.add(builder.like(builder.lower(root.get(ModeloDeLaudoDoProc_.textogeral)), "%"+ filtro.getCustomstring().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(ModeloDeLaudoDoProcFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ModeloDeLaudoDoProc> root = query.from(ModeloDeLaudoDoProc.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}
