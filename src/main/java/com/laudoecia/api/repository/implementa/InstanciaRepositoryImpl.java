package com.laudoecia.api.repository.implementa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.laudoecia.api.modelo.Instancia;
import com.laudoecia.api.modelo.Instancia_;
import com.laudoecia.api.modelo.Tagimagem;
import com.laudoecia.api.modelo.Tagimagem_;
import com.laudoecia.api.repository.resumo.ResumoInstancia;

public class InstanciaRepositoryImpl implements InstanciaRepositoryQuery{
	@Autowired
	private EntityManager em;
	
	@Override
	public ResumoInstancia ResumirPraDicom(Long idinstance) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoInstancia> criteria = builder.createQuery(ResumoInstancia.class);
		Root<Instancia> root = criteria.from(Instancia.class);
		Root<Tagimagem> rootteste = criteria.from(Tagimagem.class);
		
		criteria.orderBy(builder.asc(root.get("codigo")));
		criteria.where(builder.equal(root.get(Instancia_.codigo), idinstance));		
		criteria.where(builder.equal(root.get("idinstance"), idinstance));
		
		criteria.select(builder.construct(ResumoInstancia.class, root.get(Instancia_.codigo), 
		root.get(Instancia_.mediastoragesopinstanceuid), rootteste.get(Tagimagem_.codigo)));
		
		TypedQuery<ResumoInstancia> query = em.createQuery(criteria);
		
		return query.getSingleResult();
	}
}
