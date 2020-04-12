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

import com.laudoecia.api.domain.ProcedimentoMedico;
import com.laudoecia.api.domain.ProcedimentoMedico_;
import com.laudoecia.api.repository.filtro.ProcedimentoMedicoFilter;
import com.laudoecia.api.repository.resumo.ResumoProcedimentoMedico;

public class ProcedimentoMedicoRepositoryImpl implements ProcedimentoMedicoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<ResumoProcedimentoMedico> resumir(ProcedimentoMedicoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoProcedimentoMedico> criteria = builder.createQuery(ResumoProcedimentoMedico.class);
		Root<ProcedimentoMedico> root = criteria.from(ProcedimentoMedico.class);
		
		criteria.orderBy(builder.asc(root.get("codigo")));		
		criteria.select(builder.construct(
			ResumoProcedimentoMedico.class, root.get(ProcedimentoMedico_.codigo), root.get(ProcedimentoMedico_.nome), root.get(ProcedimentoMedico_.grupo)));
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoProcedimentoMedico> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, ProcedimentoMedicoFilter filtro, Root<ProcedimentoMedico> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.upper(root.get(ProcedimentoMedico_.nome)), "%"+ filtro.getNome().toUpperCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(ProcedimentoMedicoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ProcedimentoMedico> root = query.from(ProcedimentoMedico.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}
