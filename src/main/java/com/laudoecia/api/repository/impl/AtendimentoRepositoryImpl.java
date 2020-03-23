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

import com.laudoecia.api.domain.Atendimento;

public class AtendimentoRepositoryImpl implements AtendimentoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Atendimento> FiltroPaginado(Atendimento atendimento, Pageable page) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Atendimento> query = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = query.from(Atendimento.class);
		
		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, atendimento, root);
		query.where(predicato);
		
		TypedQuery<Atendimento> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, page);
		
		return new PageImpl<>(tiped.getResultList(), page, Total(atendimento));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, Atendimento filtro, Root<Atendimento> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(Atendimento filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Atendimento> root = query.from(Atendimento.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
