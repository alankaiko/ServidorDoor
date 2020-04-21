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

import com.laudoecia.api.domain.Atendimento;
import com.laudoecia.api.domain.Atendimento_;
import com.laudoecia.api.repository.filtro.AtendimentoFilter;

public class AtendimentoRepositoryImpl implements AtendimentoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Page<Atendimento> Filtrando(AtendimentoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		criteria.orderBy(builder.asc(root.get("codigo")));		
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<Atendimento> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, AtendimentoFilter filtro, Root<Atendimento> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		 
//		if(!StringUtils.isEmpty(filtro.getPatientname()))
//			lista.add(builder.equal(root.get(Atendimento_.patient), y))
//		
		if (filtro.getDatainicial() != null)
			lista.add(builder.greaterThanOrEqualTo(root.get(Atendimento_.datacadastro), filtro.getDatainicial()));
		
		if (filtro.getDatafinal() != null)
			lista.add(builder.lessThanOrEqualTo(root.get(Atendimento_.datacadastro), filtro.getDatafinal()));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(AtendimentoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Atendimento> root = query.from(Atendimento.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
