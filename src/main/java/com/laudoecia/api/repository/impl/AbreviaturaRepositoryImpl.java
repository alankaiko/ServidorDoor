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

import com.laudoecia.api.domain.Abreviatura;
import com.laudoecia.api.domain.Abreviatura_;
import com.laudoecia.api.repository.filtro.AbreviaturaFilter;
import com.laudoecia.api.repository.resumo.ResumoAbreviatura;

public class AbreviaturaRepositoryImpl implements AbreviaturaRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<ResumoAbreviatura> resumir(AbreviaturaFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoAbreviatura> criteria = builder.createQuery(ResumoAbreviatura.class);
		Root<Abreviatura> root = criteria.from(Abreviatura.class);
		
		criteria.orderBy(builder.asc(root.get("codigo")));		
		criteria.select(builder.construct(
			ResumoAbreviatura.class, root.get(Abreviatura_.codigo), root.get(Abreviatura_.titulo), root.get(Abreviatura_.texto)));
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoAbreviatura> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, AbreviaturaFilter filtro, Root<Abreviatura> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getTitulo()))
			lista.add(builder.like(builder.lower(root.get(Abreviatura_.titulo)), "%"+ filtro.getTitulo().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getTexto()))
			lista.add(builder.like(builder.lower(root.get(Abreviatura_.texto)), "%"+ filtro.getTexto().toLowerCase()+"%"));
	
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(AbreviaturaFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Abreviatura> root = query.from(Abreviatura.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
