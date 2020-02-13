package com.laudoecia.api.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.domain.Instance_;
import com.laudoecia.api.domain.Tagimagem;
import com.laudoecia.api.domain.Tagimagem_;
import com.laudoecia.api.repository.resumo.ResumoInstance;

public class InstanceRepositoryImpl implements InstanceRepositoryQuery{
	@Autowired
	private EntityManager em;

//	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//	CriteriaQuery<Long> query = builder.createQuery(String.class);
//	Root<Order> root = query.from(Orders.class);
//	query.select(root.get(Orders_.id));
	
	@Override
	public ResumoInstance ResumirPraDicom(Long idinstance) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ResumoInstance> criteria = builder.createQuery(ResumoInstance.class);
		Root<Instance> root = criteria.from(Instance.class);
		Root<Tagimagem> rootteste = criteria.from(Tagimagem.class);
		
		criteria.where(builder.equal(root.get(Instance_.idinstance), idinstance));
		//cq.where(cb.equal(model.get("id"), modelId));
		
		
		criteria.where(builder.equal(root.get("idinstance"), idinstance));
		
		criteria.select(builder.construct(ResumoInstance.class, root.get(Instance_.idinstance), 
			root.get(Instance_.mediastoragesopinstanceuid), rootteste.get(Tagimagem_.codigo)));
		
		
		TypedQuery<ResumoInstance> query = em.createQuery(criteria);
		
		return query.getSingleResult();
	}
	

}
