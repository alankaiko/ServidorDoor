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

import com.laudoecia.api.modelo.Sigla;
import com.laudoecia.api.modelo.Sigla_;
import com.laudoecia.api.repository.filtro.SiglaFilter;

public class SiglaRepositoryImpl implements SiglaRepositoryQuery{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean VerificarSiglaNome(String nome) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Sigla> criteria = builder.createQuery(Sigla.class);
			Root<Sigla> root = criteria.from(Sigla.class);
			
			criteria.where(builder.equal(builder.lower(root.get(Sigla_.descricao)), nome.toLowerCase()));
			TypedQuery<Sigla> query = em.createQuery(criteria);
			
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	@Override
	public Long BuscarIdMax() {
		Long codigo = em.createQuery("SELECT MAX(sigla.codigo) FROM Sigla sigla", Long.class).getSingleResult();
		return codigo;
	}

	@Override
	public Page<Sigla> Filtrando(SiglaFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Sigla> query = builder.createQuery(Sigla.class);
		Root<Sigla> root = query.from(Sigla.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<Sigla> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, SiglaFilter filtro, Root<Sigla> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getDescricao()))
			lista.add(builder.like(builder.lower(root.get(Sigla_.descricao)), "%"+ filtro.getDescricao().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(SiglaFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Sigla> root = query.from(Sigla.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}
