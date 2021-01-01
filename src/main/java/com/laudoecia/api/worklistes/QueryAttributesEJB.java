package com.laudoecia.api.worklistes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dcm4che3.net.Device;
import org.dcm4che3.util.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;

import com.laudoecia.api.domain.Instance;
import com.laudoecia.api.domain.Instance_;
import com.laudoecia.api.domain.Series;
import com.laudoecia.api.domain.Series_;
import com.laudoecia.api.domain.Study;
import com.laudoecia.api.domain.Study_;


public class QueryAttributesEJB {
	private CodeCache codeCache;
	private Device device;

	@PersistenceContext
	EntityManager em;

	public StudyQueryAttributes calculateStudyQueryAttributes(Long studyPk, QueryRetrieveView qrView) {
		System.out.println("vindo aqui");
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Tuple> q = cb.createTupleQuery();
//		Root<Series> series = q.from(Series.class);
//		String viewID = qrView.getViewID();
//		CollectionJoin<Series, SeriesQueryAttributes> seriesQueryAttributes = QueryBuilder.joinSeriesQueryAttributes(cb,
//				series, viewID);
//		TypedQuery<Tuple> query = em.createQuery(q
//				.multiselect(series.get(Series_.pk), series.get(Series_.modality),
//						seriesQueryAttributes.get(SeriesQueryAttributes_.numberOfInstances),
//						seriesQueryAttributes.get(SeriesQueryAttributes_.sopClassesInSeries),
//						seriesQueryAttributes.get(SeriesQueryAttributes_.retrieveAETs),
//						seriesQueryAttributes.get(SeriesQueryAttributes_.availability))
//				.where(cb.equal(series.get(Series_.study).get(Study_.pk), studyPk)));
//		StudyQueryAttributesBuilder builder = new StudyQueryAttributesBuilder(series, seriesQueryAttributes);
//		try (Stream<Tuple> resultStream = query.getResultStream()) {
//			resultStream.forEach(tuple -> {
//				Integer numberOfInstancesI = tuple
//						.get(seriesQueryAttributes.get(SeriesQueryAttributes_.numberOfInstances));
//				if (numberOfInstancesI == null)
//					builder.add(tuple, calculateSeriesQueryAttributes(tuple.get(series.get(Series_.pk)), qrView));
//				else
//					builder.add(tuple);
//			});
//		}
//		StudyQueryAttributes queryAttrs = builder.build();
//		queryAttrs.setViewID(qrView.getViewID());
//		queryAttrs.setStudy(em.getReference(Study.class, studyPk));
//		em.persist(queryAttrs);
//		return queryAttrs;
		return null;
	}

	public SeriesQueryAttributes calculateSeriesQueryAttributes(Long seriesPk, QueryRetrieveView qrView) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Tuple> q = cb.createTupleQuery();
//		Root<Instance> instance = q.from(Instance.class);
//		Join<Instance, Series> series = instance.join(Instance_.series);
//		Join<Series, Study> study = series.join(Series_.study);
//		QueryBuilder queryBuilder = new QueryBuilder(cb);
//		List<Predicate> x = new ArrayList<>();
//		x.add(cb.equal(series.get(Series_.pk), seriesPk));
//		queryBuilder.hideRejectedInstance(x, q, study, series, instance,
//				codeCache.findOrCreateEntities(qrView.getShowInstancesRejectedByCodes()),
//				qrView.isHideNotRejectedInstances());
//		queryBuilder.hideRejectionNote(x, instance,
//				codeCache.findOrCreateEntities(qrView.getHideRejectionNotesWithCodes()));
//		TypedQuery<Tuple> query = em
//				.createQuery(q.multiselect(instance.get(Instance_.sopClassUID), instance.get(Instance_.retrieveAETs),
//						instance.get(Instance_.availability)).where(x.toArray(new Predicate[0])));
//		SeriesQueryAttributesBuilder builder = new SeriesQueryAttributesBuilder(instance);
//		try (Stream<Tuple> resultStream = query.getResultStream()) {
//			resultStream.forEach(builder::addInstance);
//		}
//		SeriesQueryAttributes queryAttrs = builder.build();
//		queryAttrs.setViewID(qrView.getViewID());
//		queryAttrs.setSeries(em.getReference(Series.class, seriesPk));
//		em.persist(queryAttrs);
//		return queryAttrs;
		return null;
	}

	public boolean calculateStudyQueryAttributes(String studyUID) {
		Long studyPk;
		try {
			studyPk = em.createNamedQuery(Study.FIND_PK_BY_STUDY_UID, Long.class).setParameter(1, studyUID)
					.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		ArchiveDeviceExtension arcDev = device.getDeviceExtensionNotNull(ArchiveDeviceExtension.class);
		Set<String> viewIDs = new HashSet<>(arcDev.getQueryRetrieveViewIDs());
		viewIDs.removeAll(em.createNamedQuery(StudyQueryAttributes.VIEW_IDS_FOR_STUDY_PK, String.class)
				.setParameter(1, studyPk).getResultList());

		for (String viewID : viewIDs) {
			calculateStudyQueryAttributes(studyPk, arcDev.getQueryRetrieveView(viewID));
		}
		return true;
	}

	private static class SeriesQueryAttributesBuilder {
		private final Path<Instance> instance;
		private int numberOfInstances;
		private String[] retrieveAETs;
		private Availability availability;
		private Set<String> cuids = new HashSet<>();

		public SeriesQueryAttributesBuilder(Path<Instance> instance) {
			this.instance = instance;
		}

		public void addInstance(Tuple result) {
//			String[] retrieveAETs1 = StringUtils.split(result.get(instance.get(Instance_.retrieveAETs)), '\\');
//			Availability availability1 = result.get(instance.get(Instance_.availability));
//			if (numberOfInstances++ == 0) {
//				retrieveAETs = retrieveAETs1;
//				availability = availability1;
//			} else {
//				retrieveAETs = intersection(retrieveAETs, retrieveAETs1);
//				if (availability.compareTo(availability1) < 0)
//					availability = availability1;
//			}
//			cuids.add(result.get(instance.get(Instance_.sopClassUID)));
		}

		public SeriesQueryAttributes build() {
			SeriesQueryAttributes queryAttrs = new SeriesQueryAttributes();
			queryAttrs.setNumberOfInstances(numberOfInstances);
			if (numberOfInstances > 0) {
				queryAttrs.setSOPClassesInSeries(StringUtils.concat(cuids, '\\'));
				queryAttrs.setRetrieveAETs(StringUtils.concat(retrieveAETs, '\\'));
				queryAttrs.setAvailability(availability);
			}
			return queryAttrs;
		}
	}

	private static class StudyQueryAttributesBuilder {

		private final Path<Series> series;
		private final Path<SeriesQueryAttributes> seriesQueryAttributesPath;
		private int numberOfSeries;
		private int numberOfInstances;
		private String[] retrieveAETs;
		private Availability availability;
		private Set<String> mods = new HashSet<>();
		private Set<String> cuids = new HashSet<>();

		public StudyQueryAttributesBuilder(Path<Series> series, Path<SeriesQueryAttributes> seriesQueryAttributesPath) {
			this.series = series;
			this.seriesQueryAttributesPath = seriesQueryAttributesPath;
		}

		public void add(Tuple tuple) {
//			add(tuple.get(seriesQueryAttributesPath.get(SeriesQueryAttributes_.numberOfInstances)),
//					tuple.get(series.get(Series_.modality)),
//					tuple.get(seriesQueryAttributesPath.get(SeriesQueryAttributes_.sopClassesInSeries)),
//					tuple.get(seriesQueryAttributesPath.get(SeriesQueryAttributes_.retrieveAETs)),
//					tuple.get(seriesQueryAttributesPath.get(SeriesQueryAttributes_.availability)));
		}

		public void add(Tuple tuple, SeriesQueryAttributes seriesQueryAttributes) {
//			add(seriesQueryAttributes.getNumberOfInstances(), tuple.get(series.get(Series_.modality)),
//					seriesQueryAttributes.getSOPClassesInSeries(), seriesQueryAttributes.getRetrieveAETs(),
//					seriesQueryAttributes.getAvailability());
		}

		private void add(int numInstances, String modality, String sopClassesInSeries, String retrieveAETs,
				Availability availability) {
			if (numInstances == 0)
				return;

			String[] retrieveAETs1 = StringUtils.split(retrieveAETs, '\\');
			numberOfInstances += numInstances;
			if (numberOfSeries++ == 0) {
				this.retrieveAETs = retrieveAETs1;
				this.availability = availability;
			} else {
				this.retrieveAETs = intersection(this.retrieveAETs, retrieveAETs1);
				if (this.availability.compareTo(availability) < 0)
					this.availability = availability;
			}
			if (!modality.equals("*"))
				mods.add(modality);
			for (String cuid : StringUtils.split(sopClassesInSeries, '\\'))
				cuids.add(cuid);
		}

		public StudyQueryAttributes build() {
			StudyQueryAttributes queryAttrs = new StudyQueryAttributes();
			queryAttrs.setNumberOfInstances(numberOfInstances);
			if (numberOfInstances > 0) {
				queryAttrs.setNumberOfSeries(numberOfSeries);
				queryAttrs.setModalitiesInStudy(StringUtils.concat(mods, '\\'));
				queryAttrs.setSOPClassesInStudy(StringUtils.concat(cuids, '\\'));
				queryAttrs.setRetrieveAETs(StringUtils.concat(retrieveAETs, '\\'));
				queryAttrs.setAvailability(availability);
			}
			return queryAttrs;
		}

	}

	private static String[] intersection(String[] ss1, String[] ss2) {
		int l = 0;
		for (int i = 0; i < ss1.length; i++)
			if (contains(ss2, ss1[i]))
				ss1[l++] = ss1[i];

		if (l == ss1.length)
			return ss1;

		String[] ss = new String[l];
		System.arraycopy(ss1, 0, ss, 0, l);
		return ss;
	}

	private static boolean contains(String[] ss, String s0) {
		for (String s : ss)
			if (s0.equals(s))
				return true;
		return false;
	}

}
