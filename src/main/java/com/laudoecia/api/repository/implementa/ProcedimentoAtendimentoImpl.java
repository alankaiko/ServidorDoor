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

import com.laudoecia.api.modelo.ProcedimentoAtendimento;
import com.laudoecia.api.modelo.ProcedimentoAtendimento_;
import com.laudoecia.api.repository.filtro.ProcedimentoAtendimentoFilter;

public class ProcedimentoAtendimentoImpl implements ProcedimentoAtendimentoQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<ProcedimentoAtendimento> Filtrando(ProcedimentoAtendimentoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProcedimentoAtendimento> query = builder.createQuery(ProcedimentoAtendimento.class);
		Root<ProcedimentoAtendimento> root = query.from(ProcedimentoAtendimento.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<ProcedimentoAtendimento> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, ProcedimentoAtendimentoFilter filtro, Root<ProcedimentoAtendimento> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(filtro.getDataexecucao() != null)
			lista.add(builder.greaterThanOrEqualTo(root.get(ProcedimentoAtendimento_.dataexecucao), filtro.getDataexecucao()));
		
		if(filtro.getPreventregalaudo() != null)
			lista.add(builder.greaterThanOrEqualTo(root.get(ProcedimentoAtendimento_.preventregalaudo), filtro.getPreventregalaudo()));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(ProcedimentoAtendimentoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ProcedimentoAtendimento> root = query.from(ProcedimentoAtendimento.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	

}
