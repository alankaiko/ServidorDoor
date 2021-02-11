package com.laudoecia.api.repository.implementa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.laudoecia.api.modelo.Serie;


public class SerieRepositoryImpl implements SerieRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Serie> ListarMaximoCom(int primeiro, int maximo) {
		TypedQuery<Serie> tiped = em.createQuery("SELECT FROM Series serie order by datemodify", Serie.class);
		return tiped.setFirstResult(primeiro).setMaxResults(maximo).getResultList();
	}

	@Override
	public Serie BuscarPorSeriesinstanceuid(String seriesinstanceuid, Integer seriesnumber) {
		try {
			return this.em.createQuery("SELECT serie FROM Series serie WHERE serie.seriesnumber = :seriesnumber AND serie.seriesinstanceuid = :seriesinstanceuid", Serie.class)
					.setParameter("seriesnumber", seriesnumber)
					.setParameter("seriesinstanceuid", seriesinstanceuid)
					.getSingleResult();
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

}
