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

import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.domain.ProfissionalExecutante_;
import com.laudoecia.api.repository.filtro.ProfissionalExecutanteFilter;
import com.laudoecia.api.repository.resumo.ResumoProfissionalExecutante;

public class ProfissionalExecutanteRepositoryImpl implements ProfissionalExecutanteRepositoryQuery{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Page<ProfissionalExecutante> filtrando(ProfissionalExecutanteFilter filtro, Pageable page) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProfissionalExecutante> query = builder.createQuery(ProfissionalExecutante.class);
		Root<ProfissionalExecutante> root = query.from(ProfissionalExecutante.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<ProfissionalExecutante> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, page);
		
		return new PageImpl<>(tiped.getResultList(), page, Total(filtro));
	}

	@Override
	public Page<ResumoProfissionalExecutante> resumir(ProfissionalExecutanteFilter filtro, Pageable page) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoProfissionalExecutante> criteria = builder.createQuery(ResumoProfissionalExecutante.class);
		Root<ProfissionalExecutante> root = criteria.from(ProfissionalExecutante.class);
		
		criteria.orderBy(builder.asc(root.get("codigo")));		
		criteria.select(builder.construct(
				ResumoProfissionalExecutante.class, root.get(ProfissionalExecutante_.codigo), root.get(ProfissionalExecutante_.nome), root.get(ProfissionalExecutante_.numnoconselho), root.get(ProfissionalExecutante_.contato)));
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoProfissionalExecutante> query = em.createQuery(criteria);
		AdicionarPaginacao(query, page);
		
		return new PageImpl<>(query.getResultList(), page, Total(filtro));
	}
	
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, ProfissionalExecutanteFilter filtro, Root<ProfissionalExecutante> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.lower(root.get(ProfissionalExecutante_.nome)), "%"+ filtro.getNome().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNumnoconselho()))
			lista.add(builder.like(builder.lower(root.get(ProfissionalExecutante_.numnoconselho)), "%"+ filtro.getNumnoconselho().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(ProfissionalExecutanteFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ProfissionalExecutante> root = query.from(ProfissionalExecutante.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}


}
