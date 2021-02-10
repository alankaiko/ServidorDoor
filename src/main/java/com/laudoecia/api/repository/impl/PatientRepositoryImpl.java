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

import com.laudoecia.api.domain.Paciente;
import com.laudoecia.api.domain.Paciente_;
import com.laudoecia.api.repository.filtro.PatientFilter;
import com.laudoecia.api.repository.resumo.ResumoPatient;


public class PatientRepositoryImpl implements PatientRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Paciente> ListarMaximoCom(int primeiro, int maximo) {
		try {
			TypedQuery<Paciente> tiped = em.createQuery("FROM Patient patient order by datemodify", Paciente.class);
			return tiped.setFirstResult(primeiro).setMaxResults(maximo).getResultList();
		} catch (Exception e) {
			System.out.println("deu erro aqui");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Page<ResumoPatient> Resumir(PatientFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoPatient> criteria = builder.createQuery(ResumoPatient.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		
		criteria.orderBy(builder.asc(root.get("idpatient")));
		criteria.select(builder.construct(ResumoPatient.class, root.get(Paciente_.idpatient), 
			root.get(Paciente_.patientid), root.get(Paciente_.nome), root.get(Paciente_.datanasc),
			root.get(Paciente_.idade), root.get(Paciente_.sexo), root.get(Paciente_.datacriacao)));
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoPatient> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}

	@Override
	public Page<Paciente> Filtrar(PatientFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Paciente> query = builder.createQuery(Paciente.class);
		Root<Paciente> root = query.from(Paciente.class);
		
		query.orderBy(builder.asc(root.get("idpatient")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<Paciente> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, PatientFilter filtro, Root<Paciente> root) {
		List<Predicate> lista = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getPatientid()))
			lista.add(builder.like(builder.lower(root.get(Paciente_.patientid)), "%" + filtro.getPatientid().toLowerCase() + "%"));
		
		if(!StringUtils.isEmpty(filtro.getPatientname()))
			lista.add(builder.like(builder.lower(root.get(Paciente_.nome)), "%" + filtro.getPatientname().toLowerCase() +"%"));
		
		
		if(!StringUtils.isEmpty(filtro.getPatientage()))
			lista.add(builder.like(builder.lower(root.get(Paciente_.idade)), "%" + filtro.getPatientage().toLowerCase() + "%"));
		
		if(filtro.getBirthday() != null) {
			lista.add(builder.equal(root.get(Paciente_.datanasc), filtro.getBirthday()));
		}
		
		if(filtro.isServidor())
			lista.add(builder.isNotNull(root.get(Paciente_.patientid)));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable pageable) {
		int paginaatual = pageable.getPageNumber();
		int totalporpagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(PatientFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Paciente> root = query.from(Paciente.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}










