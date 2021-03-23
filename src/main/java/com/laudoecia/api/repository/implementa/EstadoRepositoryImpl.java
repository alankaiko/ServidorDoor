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

import com.laudoecia.api.modelo.Estado;
import com.laudoecia.api.modelo.Estado_;
import com.laudoecia.api.repository.filtro.EstadoFilter;

public class EstadoRepositoryImpl implements EstadoRepositoryQuery {
	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean VerificarEstadoNome(String nome) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Estado> criteria = builder.createQuery(Estado.class);
			Root<Estado> root = criteria.from(Estado.class);
			
			criteria.where(builder.equal(builder.lower(root.get(Estado_.uf)), nome.toLowerCase()));
			TypedQuery<Estado> query = em.createQuery(criteria);
			
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	@Override
	public Long BuscarIdMax() {
		Long codigo = em.createQuery("SELECT MAX(estado.codigo) FROM Estado estado", Long.class).getSingleResult();
		return codigo;
	}

	@Override
	public Page<Estado> Filtrando(EstadoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Estado> query = builder.createQuery(Estado.class);
		Root<Estado> root = query.from(Estado.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root, true);
		query.where(predicato);

		TypedQuery<Estado> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);

		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, EstadoFilter filtro, Root<Estado> root, boolean usarlike) {
		List<Predicate> lista = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(filtro.getUf()) && usarlike)
			lista.add(builder.like(builder.lower(root.get(Estado_.uf)), "%" + filtro.getUf().toLowerCase() + "%"));
		
		if (!StringUtils.isEmpty(filtro.getUf()) && !usarlike)
			lista.add(builder.equal(builder.lower(root.get(Estado_.uf)), filtro.getUf().toLowerCase()));

		if (!StringUtils.isEmpty(filtro.getDescricao()) && usarlike)
			lista.add(builder.like(builder.lower(root.get(Estado_.descricao)),"%" + filtro.getDescricao().toLowerCase() + "%"));
		
		if (!StringUtils.isEmpty(filtro.getDescricao()) && !usarlike)
			lista.add(builder.equal(builder.lower(root.get(Estado_.descricao)), filtro.getDescricao().toLowerCase()));

		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(EstadoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Estado> root = query.from(Estado.class);

		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root, true);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
}
