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

import com.laudoecia.api.modelo.SubcategoriaCid10;
import com.laudoecia.api.modelo.SubcategoriaCid10_;
import com.laudoecia.api.repository.filtro.SubcategoriaCid10Filter;
import com.laudoecia.api.repository.resumo.ResumoSubcategoriaCid10;

public class SubcategoriaCid10RepositoryImpl implements SubcategoriaCid10RepositoryQuery{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Long BuscarIdMax() {
		Long codigo = em.createQuery("SELECT MAX(subcategoria.codigo) FROM SubcategoriaCid10 subcategoria", Long.class).getSingleResult();
		return codigo;
	}
	
	@Override
	public Page<ResumoSubcategoriaCid10> Resumir(SubcategoriaCid10Filter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoSubcategoriaCid10> criteria = builder.createQuery(ResumoSubcategoriaCid10.class);
		Root<SubcategoriaCid10> root = criteria.from(SubcategoriaCid10.class);
		
		criteria.orderBy(builder.asc(root.get("codigo")));		
		criteria.select(builder.construct(
			ResumoSubcategoriaCid10.class, root.get(SubcategoriaCid10_.codigo), root.get(SubcategoriaCid10_.nome), root.get(SubcategoriaCid10_.categoriacid10)));
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoSubcategoriaCid10> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}

	@Override
	public Page<SubcategoriaCid10> Filtrando(SubcategoriaCid10Filter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<SubcategoriaCid10> query = builder.createQuery(SubcategoriaCid10.class);
		Root<SubcategoriaCid10> root = query.from(SubcategoriaCid10.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<SubcategoriaCid10> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, SubcategoriaCid10Filter filtro, Root<SubcategoriaCid10> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getCodigotexto()))
			lista.add(builder.like(builder.upper(root.get(SubcategoriaCid10_.codigotexto)), "%"+ filtro.getCodigotexto().toUpperCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.lower(root.get(SubcategoriaCid10_.nome)), "%"+ filtro.getNome().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(SubcategoriaCid10Filter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<SubcategoriaCid10> root = query.from(SubcategoriaCid10.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}
