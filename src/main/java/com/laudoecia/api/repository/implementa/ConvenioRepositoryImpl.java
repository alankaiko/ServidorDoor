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

import com.laudoecia.api.modelo.Convenio;
import com.laudoecia.api.modelo.Convenio_;
import com.laudoecia.api.repository.filtro.ConvenioFilter;
import com.laudoecia.api.repository.resumo.ResumoConvenio;

public class ConvenioRepositoryImpl implements ConvenioRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<ResumoConvenio> resumir(ConvenioFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoConvenio> criteria = builder.createQuery(ResumoConvenio.class);
		Root<Convenio> root = criteria.from(Convenio.class);
		
		criteria.orderBy(builder.asc(root.get("codigo")));		
		criteria.select(builder.construct(
				ResumoConvenio.class, root.get(Convenio_.codigo), root.get(Convenio_.nome), root.get(Convenio_.telefone), root.get(Convenio_.ativo)));
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoConvenio> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, ConvenioFilter filtro, Root<Convenio> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.lower(root.get(Convenio_.nome)), "%"+ filtro.getNome().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getTelefone()))
			lista.add(builder.like(builder.lower(root.get(Convenio_.telefone)), "%"+ filtro.getTelefone().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNomedocontato()))
			lista.add(builder.like(builder.lower(root.get(Convenio_.nomedocontato)), "%"+ filtro.getNomedocontato().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getEmail()))
			lista.add(builder.like(builder.lower(root.get(Convenio_.email)), "%"+ filtro.getEmail().toLowerCase()+"%"));
		
		if(filtro.isAtivo())
			lista.add(builder.isTrue(root.get(Convenio_.ativo)));	
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(ConvenioFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Convenio> root = query.from(Convenio.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
