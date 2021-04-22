package com.laudoecia.api.repository.implementa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.laudoecia.api.modelo.Atendimento;
import com.laudoecia.api.modelo.Atendimento_;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.modelo.Paciente_;
import com.laudoecia.api.modelo.ProfissionalSolicitante;
import com.laudoecia.api.modelo.ProfissionalSolicitante_;
import com.laudoecia.api.repository.filtro.AtendimentoFilter;

public class AtendimentoRepositoryImpl implements AtendimentoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean VerificarPacienteNome(AtendimentoFilter filtro) {
		boolean valor = false;

		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
			Root<Atendimento> root = criteria.from(Atendimento.class);
			Join<Atendimento, Paciente> rootpaciente = root.join(Atendimento_.paciente);
			Join<Atendimento, ProfissionalSolicitante> rootsolicitante = root.join(Atendimento_.solicitante);
			
			Predicate[] predicato = AdicionarRestricoes(builder, filtro, root, rootpaciente, rootsolicitante);
			criteria.where(predicato);
			
			TypedQuery<Atendimento> tiped = em.createQuery(criteria);
			
			if(tiped.getResultList().size() > 0)
				valor = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valor;
	}
	
	@Override
	public Page<Atendimento> Filtrando(AtendimentoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Atendimento> criteria = builder.createQuery(Atendimento.class);
		Root<Atendimento> root = criteria.from(Atendimento.class);
		Join<Atendimento, Paciente> rootpaciente = root.join(Atendimento_.paciente);
		Join<Atendimento, ProfissionalSolicitante> rootsolicitante = root.join(Atendimento_.solicitante);
		//criteria.orderBy(builder.desc(root.get("codigo")));		
		
		Predicate[] predicates = AdicionarRestricoes(builder, filtro, root, rootpaciente, rootsolicitante);
		criteria.where(predicates);
		
		TypedQuery<Atendimento> query = em.createQuery(criteria);
		AdicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, Total(filtro));
	}
	
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, AtendimentoFilter filtro, Root<Atendimento> root,
			Join<Atendimento, Paciente> rootpaciente, Join<Atendimento, ProfissionalSolicitante> rootsolicitante) {
		
		List<Predicate> lista= new ArrayList<Predicate>();
		
		//root
		if (filtro.getDatainicial() != null)
			lista.add(builder.greaterThanOrEqualTo(root.get(Atendimento_.datacadastro), filtro.getDatainicial()));
		
		//root
		if (filtro.getDatafinal() != null)
			lista.add(builder.lessThanOrEqualTo(root.get(Atendimento_.datacadastro), filtro.getDatafinal()));
		
		//rootpaciente
		if(!StringUtils.isEmpty(filtro.getPacientenome()))
			lista.add(builder.like(builder.lower(rootpaciente.get(Paciente_.nome)), "%" + filtro.getPacientenome().toLowerCase() + "%"));
		
		//rootpaciente
		if(filtro.getDatanascpaciente() != null)
			lista.add(builder.equal(rootpaciente.get(Paciente_.datanasc), filtro.getDatanascpaciente()));
		
		//rootsolicitante
		if(filtro.getSolicitantenome() != null)
			lista.add(builder.like(builder.lower(rootsolicitante.get(ProfissionalSolicitante_.nome)), "%" + filtro.getSolicitantenome().toLowerCase() + "%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(AtendimentoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Atendimento> root = query.from(Atendimento.class);
		Join<Atendimento, Paciente> rootpaciente = root.join(Atendimento_.paciente);
		Join<Atendimento, ProfissionalSolicitante> rootsolicitante = root.join(Atendimento_.solicitante);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root, rootpaciente, rootsolicitante);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
