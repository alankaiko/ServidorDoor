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

import com.laudoecia.api.modelo.ProfissionalSolicitante;
import com.laudoecia.api.modelo.ProfissionalSolicitante_;
import com.laudoecia.api.modelo.TISSConselho;
import com.laudoecia.api.modelo.TISSConselho_;
import com.laudoecia.api.repository.filtro.ProfissionalSolicitanteFilter;

public class ProfissionalSolicitanteRepositoryImpl implements ProfissionalSolicitanteRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean VerificarProcSolNome(String nome) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<ProfissionalSolicitante> criteria = builder.createQuery(ProfissionalSolicitante.class);
			Root<ProfissionalSolicitante> root = criteria.from(ProfissionalSolicitante.class);
			
			criteria.where(builder.equal(builder.lower(root.get(ProfissionalSolicitante_.nome)), nome.toLowerCase()));
			TypedQuery<ProfissionalSolicitante> query = em.createQuery(criteria);
			
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	@Override
	public Page<ProfissionalSolicitante> Filtrando(ProfissionalSolicitanteFilter filtro, Pageable page) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProfissionalSolicitante> query = builder.createQuery(ProfissionalSolicitante.class);
		Root<ProfissionalSolicitante> root = query.from(ProfissionalSolicitante.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<ProfissionalSolicitante> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, page);
		
		return new PageImpl<>(tiped.getResultList(), page, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, ProfissionalSolicitanteFilter filtro, Root<ProfissionalSolicitante> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		CriteriaQuery<TISSConselho> criteria = builder.createQuery(TISSConselho.class);
		Root<TISSConselho> rootgrupo = criteria.from(TISSConselho.class);
		
		if(!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.lower(root.get(ProfissionalSolicitante_.nome)), "%"+ filtro.getNome().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNumnoconselho()))
			lista.add(builder.like(builder.lower(rootgrupo.get(TISSConselho_.descricao)), "%"+ filtro.getNumnoconselho().toLowerCase()+"%"));
	
		
		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(ProfissionalSolicitanteFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ProfissionalSolicitante> root = query.from(ProfissionalSolicitante.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
}
