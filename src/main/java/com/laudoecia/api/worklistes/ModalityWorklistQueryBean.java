package com.laudoecia.api.worklistes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;


import org.dcm4chee.archive.persistence.QPatient;
import org.dcm4chee.archive.persistence.QRequestedProcedure;
import org.dcm4chee.archive.persistence.QScheduledProcedureStep;
import org.dcm4chee.archive.persistence.QServiceRequest;
import org.dcm4chee.archive.persistence.QVisit;
import org.dcm4chee.archive.persistence.Utils;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.ejb.HibernateEntityManagerFactory;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ModalityWorklistQueryBean implements ModalityWorklistQuery {
    private DataSource dataSource;
    private EntityManagerFactory emf;
    private Connection connection;
    private StatelessSession session;
    private ScrollableResults results;
    private boolean optionalKeyNotSupported;
    private boolean hasNext;

    @PostConstruct
    protected void init() {
        SessionFactory sessionFactory = ((HibernateEntityManagerFactory) emf).getSessionFactory();
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new EJBException(e);
        }
        session = sessionFactory.openStatelessSession(connection);
    }

    @Override
    public void findScheduledProcedureSteps(IDWithIssuer[] pids, Attributes keys, QueryParam queryParam) {
        BooleanBuilder builder = new BooleanBuilder();
        Builder.addPatientLevelPredicates(builder, pids, keys, queryParam);
        Builder.addServiceRequestPredicates(builder, keys, queryParam);
        Builder.addRequestedProcedurePredicates(builder, keys, queryParam);
        Attributes spsItem = keys.getNestedDataset(Tag.ScheduledProcedureStepSequence);
        Builder.addScheduledProcedureStepPredicates(builder, spsItem, queryParam);
        results = new HibernateQuery(session)
            .from(QScheduledProcedureStep.scheduledProcedureStep)
            .innerJoin(QScheduledProcedureStep.scheduledProcedureStep.requestedProcedure,
                    QRequestedProcedure.requestedProcedure)
            .innerJoin(QRequestedProcedure.requestedProcedure.serviceRequest,
                    QServiceRequest.serviceRequest)
            .innerJoin(QServiceRequest.serviceRequest.visit, QVisit.visit)
            .innerJoin(QVisit.visit.patient, QPatient.patient)
            .where(builder)
            .scroll(ScrollMode.FORWARD_ONLY,
                    QScheduledProcedureStep.scheduledProcedureStep.encodedAttributes,
                    QRequestedProcedure.requestedProcedure.encodedAttributes,
                    QServiceRequest.serviceRequest.encodedAttributes,
                    QVisit.visit.encodedAttributes,
                    QPatient.patient.encodedAttributes);
        hasNext = results.next();
    }

    @Override
    public boolean optionalKeyNotSupported() {
        checkResults();
        return optionalKeyNotSupported;
    }

    @Override
    public boolean hasMoreMatches() {
        checkResults();
        return hasNext;
    }

    @Override
    public Attributes nextMatch() {
        checkResults();
        if (!hasNext)
            throw new NoSuchElementException();
        Attributes attrs = toAttributes(results);
        hasNext = results.next();
        return attrs;
    }

    private Attributes toAttributes(ScrollableResults results2) {
        byte[] spsAttributes = results.getBinary(0);
        byte[] reqProcAttributes = results.getBinary(1);
        byte[] requestAttributes = results.getBinary(2);
        byte[] visitAttributes = results.getBinary(3);
        byte[] patientAttributes = results.getBinary(4);
        Attributes attrs = new Attributes();
        Utils.decodeAttributes(attrs, patientAttributes);
        Utils.decodeAttributes(attrs, visitAttributes);
        Utils.decodeAttributes(attrs, requestAttributes);
        Utils.decodeAttributes(attrs, reqProcAttributes);
        Attributes spsItem = Utils.decodeAttributes(spsAttributes);
        attrs.newSequence(Tag.ScheduledProcedureStepSequence, 1).add(spsItem );
        return attrs;
    }

    @Override
    @Remove
    public void close() {
        StatelessSession s = session;
        Connection c = connection;
        connection = null;
        session = null;
        results = null;
        s.close();
        try {
            c.close();
        } catch (SQLException e) {
            throw new EJBException(e);
        }
    }

    private void checkResults() {
        if (results == null)
            throw new IllegalStateException("results not initalized");
    }
}
