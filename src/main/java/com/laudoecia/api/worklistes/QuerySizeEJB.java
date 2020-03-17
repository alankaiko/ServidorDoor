package com.laudoecia.api.worklistes;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.dcm4che3.util.StringUtils;

import com.laudoecia.api.domain.Series;
import com.laudoecia.api.domain.Study;


public class QuerySizeEJB {

    private static final Long ZERO = Long.valueOf(0L);

    @PersistenceContext
    EntityManager em;

    public long calculateStudySize(Long studyPk) {
        for (Long seriesPk : em.createNamedQuery(Series.SERIES_PKS_OF_STUDY_WITH_UNKNOWN_SIZE, Long.class)
                .setParameter(1, studyPk)
                .getResultList()) {
            calculateSeriesSize(seriesPk);
        }
        Long size = StringUtils.maskNull(
                em.createNamedQuery(Series.SIZE_OF_STUDY, Long.class)
                    .setParameter(1, studyPk)
                    .getSingleResult(),
                ZERO);
        em.createNamedQuery(Study.SET_STUDY_SIZE)
                .setParameter(1, studyPk)
                .setParameter(2, size)
                .executeUpdate();
        return size;
    }

    public long calculateSeriesSize(Long seriesPk) {
        Object result = em.createNamedQuery(Location.SIZE_OF_SERIES)
                .setParameter(1, seriesPk)
                .setParameter(2, Location.ObjectType.DICOM_FILE.ordinal())
                .getSingleResult();
        long size = result instanceof Number ? ((Number) result).longValue() : 0L;
        em.createNamedQuery(Series.SET_SERIES_SIZE)
                .setParameter(1, seriesPk)
                .setParameter(2, size)
                .executeUpdate();
        return size;
    }

    public long calculateStudySize(String studyUID) {
        Long studyPk;
        try {
            studyPk = em.createNamedQuery(Study.FIND_PK_BY_STUDY_UID, Long.class)
                    .setParameter(1, studyUID)
                    .getSingleResult();
        } catch (NoResultException e) {
            return -1L;
        }
        return calculateStudySize(studyPk);
    }
}
