package com.laudoecia.api.repository.implementa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

import com.laudoecia.api.modelo.TextoPessoal;
import com.laudoecia.api.modelo.TextoPessoal_;
import com.laudoecia.api.repository.filtro.TextoPessoalFilter;

public class TextoPessoalRepositoryImpl implements TextoPessoalRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	
	@Override
	public boolean VerificarTextoPessoalNome(String nome) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<TextoPessoal> criteria = builder.createQuery(TextoPessoal.class);
			Root<TextoPessoal> root = criteria.from(TextoPessoal.class);
			
			criteria.where(builder.equal(builder.lower(root.get(TextoPessoal_.abreviatura)), nome.toLowerCase()));
			TypedQuery<TextoPessoal> query = em.createQuery(criteria);
			
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	@Override
	public Page<TextoPessoal> Filtrando(TextoPessoalFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TextoPessoal> query = builder.createQuery(TextoPessoal.class);
		Root<TextoPessoal> root = query.from(TextoPessoal.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<TextoPessoal> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, TextoPessoalFilter filtro, Root<TextoPessoal> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getAbreviatura()))
			lista.add(builder.like(builder.lower(root.get(TextoPessoal_.abreviatura)), "%"+ filtro.getAbreviatura().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getTexto()))
			lista.add(builder.like(builder.lower(root.get(TextoPessoal_.texto)), "%"+ filtro.getTexto().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(TextoPessoalFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<TextoPessoal> root = query.from(TextoPessoal.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
}
