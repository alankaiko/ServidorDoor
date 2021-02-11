package com.laudoecia.api.repository.implementa;

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

import com.laudoecia.api.modelo.Abreviatura;
import com.laudoecia.api.modelo.Abreviatura_;
import com.laudoecia.api.repository.filtro.AbreviaturaFilter;

public class AbreviaturaRepositoryImpl implements AbreviaturaRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Abreviatura> Filtrando(AbreviaturaFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Abreviatura> query = builder.createQuery(Abreviatura.class);
		Root<Abreviatura> root = query.from(Abreviatura.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<Abreviatura> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
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
