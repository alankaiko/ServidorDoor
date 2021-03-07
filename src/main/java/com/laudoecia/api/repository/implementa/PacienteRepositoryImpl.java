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

import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.modelo.Paciente_;
import com.laudoecia.api.repository.filtro.PacienteFilter;
import com.laudoecia.api.repository.resumo.ResumoPaciente;


public class PacienteRepositoryImpl implements PacienteRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Paciente> ListarMaximoCom(int primeiro, int maximo) {
		try {
			TypedQuery<Paciente> tiped = em.createQuery("FROM Patient patient order by datemodify", Paciente.class);
			return tiped.setFirstResult(primeiro).setMaxResults(maximo).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Page<ResumoPaciente> Resumir(PacienteFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoPaciente> criteria = builder.createQuery(ResumoPaciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		
		criteria.orderBy(builder.asc(root.get("codigo")));
		criteria.select(builder.construct(ResumoPaciente.class, root.get(Paciente_.codigo), 
			root.get(Paciente_.pacienteid), root.get(Paciente_.nome), root.get(Paciente_.datanasc),
			root.get(Paciente_.idade), root.get(Paciente_.sexo), root.get(Paciente_.datacriacao)));
		

		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoPaciente> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}

	@Override
	public Page<Paciente> Filtrar(PacienteFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Paciente> query = builder.createQuery(Paciente.class);
		Root<Paciente> root = query.from(Paciente.class);
		
		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<Paciente> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, PacienteFilter filtro, Root<Paciente> root) {
		List<Predicate> lista = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getPacienteid()))
			lista.add(builder.like(builder.lower(root.get(Paciente_.pacienteid)), "%" + filtro.getPacienteid().toLowerCase() + "%"));
		
		if(!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.lower(root.get(Paciente_.nome)), "%" + filtro.getNome().toLowerCase() +"%"));
		
		
		if(!StringUtils.isEmpty(filtro.getIdade()))
			lista.add(builder.like(builder.lower(root.get(Paciente_.idade)), "%" + filtro.getIdade().toLowerCase() + "%"));
		
		if(filtro.getDatanasc() != null) {
			lista.add(builder.equal(root.get(Paciente_.datanasc), filtro.getDatanasc()));
		}
		
		if(filtro.isServidor())
			lista.add(builder.isNotNull(root.get(Paciente_.codigo)));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable pageable) {
		int paginaatual = pageable.getPageNumber();
		int totalporpagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(PacienteFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Paciente> root = query.from(Paciente.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}	
}










