package com.laudoecia.api.worklistes;

import java.util.Iterator;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.util.StringUtils;
import org.hibernate.annotations.QueryHints;


abstract class AbstractQuery implements Query {

    protected final QueryContext context;
    protected final EntityManager em;
    protected final CriteriaBuilder cb;
    protected final QueryBuilder builder;
    private Stream<Tuple> resultStream;
    private Iterator<Tuple> results;
    private int offset;
    private int limit;
    private int fetchSize;
    private int rejected;
    private int matches;

    AbstractQuery(QueryContext context, EntityManager em) {
        this.context = context;
        this.em = em;
        this.cb = em.getCriteriaBuilder();
        this.builder = new QueryBuilder(cb);
    }

    @Override
    public QueryContext getQueryContext() {
        return context;
    }

    @Override
    public void executeQuery(int fetchSize) {
        executeQuery(fetchSize, 0, -1);
    }

    @Override
    public void executeQuery(int fetchSize, int offset, int limit) {
        this.fetchSize = fetchSize;
        this.offset = offset;
        this.limit = limit;
        rejected = 0;
        matches = 0;
        close(resultStream);
        TypedQuery<Tuple> query = em.createQuery(multiselect())
                .setHint(QueryHints.FETCH_SIZE, fetchSize);
        if (offset > 0)
            query.setFirstResult(offset);
        if (limit > 0)
            query.setMaxResults(limit);
        resultStream = query.getResultStream();
        results = resultStream.iterator();
    }

    @Override
    public long fetchCount() {
        return QueryBuilder.unbox(em.createQuery(count()).getSingleResult(), 0L);
    }

    @Override
    public long fetchSize() {
        return QueryBuilder.unbox(em.createQuery(sumStudySize()).getSingleResult(), 0L);
    }

    @Override
    public Stream<Long> withUnknownSize(int fetchSize) {
        return em.createQuery(withUnknownSize())
                .setHint(QueryHints.FETCH_SIZE, fetchSize)
                .getResultStream();
    }

    @Override
    public boolean hasMoreMatches() throws DicomServiceException {
        boolean hasNext = results.hasNext();
        if (hasNext || rejected == 0 || limit != matches)
            return hasNext;

        executeQuery(fetchSize, offset + matches, rejected);
        return results.hasNext();
    }

    @Override
    public Attributes nextMatch() {
        Attributes attrs = toAttributes(results.next());
        matches++;
        if (attrs == null)
            rejected++;
        return attrs;
    }

    @Override
    public Attributes adjust(Attributes match) {
        if (match == null)
            return null;

        Attributes returnKeys = context.getReturnKeys();
        if (returnKeys == null)
            return match;

        Attributes filtered = new Attributes(returnKeys.size());
        filtered.addSelected(match, returnKeys);
        return filtered;
    }

    @Override
    public void close() {
        close(resultStream);
        context.close();
    }

    private void close(Stream<Tuple> resultStream) {
        if (resultStream != null)
            resultStream.close();
    }

    protected abstract CriteriaQuery<Tuple> multiselect();

    protected abstract CriteriaQuery<Long> count();

    protected CriteriaQuery<Long> sumStudySize() {
        throw new UnsupportedOperationException();
    }

    protected CriteriaQuery<Long> withUnknownSize() {
        throw new UnsupportedOperationException();
    }

    protected abstract Attributes toAttributes(Tuple results);

    static String[] splitAndAppend(String s, String append) {
        String[] ss = StringUtils.split(s, '\\');
        if (append != null && !append.equals("*")) {
            String[] src = ss;
            ss = new String[src.length+1];
            System.arraycopy(src, 0, ss, 0, src.length);
            ss[src.length] = append;
        }
        return ss;
    }

    protected String[] retrieveAETs(String retrieveAETs, String externalRetrieveAET) {
        String[] aets = context.getArchiveAEExtension().returnRetrieveAETitles();
        return aets.length > 0 ? aets : splitAndAppend(retrieveAETs, externalRetrieveAET);
    }
}
