package com.laudoecia.api.sistemdicom.worklist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.service.DicomServiceException;

import com.laudoecia.api.modelo.Atendimento;
import com.laudoecia.api.modelo.Atendimento_;
import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.sistemdicom.interfaces.ListaDeTrabalhoQuery;

public class ListaDeTrabalhoImpl implements ListaDeTrabalhoQuery{
	protected final EntityManager conexaobanco;
    protected final QueryContext contexto;
    protected final CriteriaBuilder criteriabuilder;
    private Stream<Atendimento> resultado;
    private Iterator<Atendimento> itera;
    private int desl;
    private int limite;
    private int tamanho;
    private int rejeicao;
    private int valores;
    
	public ListaDeTrabalhoImpl(QueryContext contexto, EntityManager conexaobanco) {
		this.contexto = contexto;
        this.conexaobanco = conexaobanco;
        this.criteriabuilder = this.conexaobanco.getCriteriaBuilder();
	}

	public Attributes CriarAtributos(Atendimento atendimento) {
		try {
			ConverterParaAtributo converte = new ConverterParaAtributo();
			Attributes atributos = converte.ConverterParaAtributos(atendimento);
			
			converte = null;
			return atributos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

    @Override
    public void ExecutarRequisicao(int tamanho) {
        this.ExecutarRequisicao(tamanho, 0, -1);
    }

    @Override
    public void ExecutarRequisicao(int tamanho, int desl, int limite) {
        this.tamanho = tamanho;
        this.desl = desl;
        this.limite = limite;
        this.rejeicao = 0;
        this.valores = 0;
        
		CriteriaQuery<Atendimento> query = this.criteriabuilder.createQuery(Atendimento.class);
		Root<Atendimento> root = query.from(Atendimento.class);
		Join<Atendimento, Paciente> rootpatient = root.join(Atendimento_.paciente);
		query.orderBy(this.criteriabuilder.asc(root.get("codigo")));
		
		Predicate[] predicato = AdicionarRestricoes(this.criteriabuilder, root, rootpatient);
		query.where(predicato);
		
		TypedQuery<Atendimento> tiped = this.conexaobanco.createQuery(query);
		resultado = tiped.getResultStream();
        itera = resultado.iterator();
    }
	    
    private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, Root<Atendimento> root, Join<Atendimento, Paciente> rootpatient) {
		List<Predicate> lista = new ArrayList<Predicate>();
	
//			if (!StringUtils.isEmpty(filtro.getDescricao()))
//				lista.add(builder.like(builder.lower(root.get(RegistroMovimentacao_.descricao)), "%" + filtro.getDescricao().toLowerCase() + "%"));
//	
//			if (!StringUtils.isEmpty(filtro.getDataregistro())) {
//				lista.add(builder.greaterThanOrEqualTo(root.get(RegistroMovimentacao_.dataregistro), filtro.getDataregistro()));
//			}
//			
//			if (!StringUtils.isEmpty(filtro.getDataregistro())) {
//				lista.add(builder.lessThanOrEqualTo(root.get(RegistroMovimentacao_.dataregistro), filtro.getDataregistro()));
//			}
//	
//			if (!StringUtils.isEmpty(filtro.getSala()))
//				lista.add(builder.equal(builder.lower(rootsala.get(Sala_.sala)), filtro.getSala().toLowerCase()));
	
		return lista.toArray(new Predicate[lista.size()]);
    }

    @Override
    public boolean ExisteMaisAtributos() throws DicomServiceException {
    	 boolean hasNext = itera.hasNext();
         if (hasNext || this.rejeicao == 0 || this.limite != this.valores)
             return hasNext;

         this.ExecutarRequisicao(this.tamanho, this.desl + this.valores, this.rejeicao);
         return itera.hasNext();
    }

    @Override
    public Attributes AdicionarAtributos() {
        Attributes attrs = CriarAtributos(this.itera.next());
        this.valores++;
        
        if (attrs == null)
            this.rejeicao++;
        
        return attrs;
    }

    @Override
    public Attributes Ajustar(Attributes atributos) {
        if (atributos == null)
            return null;

        Attributes returnKeys = this.contexto.getReturnKeys();
        if (returnKeys == null)
            return atributos;

        Attributes filtered = new Attributes(returnKeys.size());
        filtered.addSelected(atributos, returnKeys);
        return filtered;
    }

    @Override
	public boolean ChaveOpcionNaoSuportada() {
		return false;
	}

    @Override
    public QueryContext RetornaQueryContexto() {
        return this.contexto;
    }
    
    @Override
    public void close() {
        this.contexto.close();
        this.conexaobanco.close();
    }
}
