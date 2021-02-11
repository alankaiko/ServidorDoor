package com.laudoecia.api.repository.implementa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.laudoecia.api.modelo.Estudo;

public class EstudoRepositoryImpl implements EstudoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Estudo> ListarMaximoCom(int primeiro, int maximo) {
		TypedQuery<Estudo> tiped = em.createQuery("SELECT FROM Study study order by datemodify", Estudo.class);
		return tiped.setFirstResult(primeiro).setMaxResults(maximo).getResultList();
	}

}
