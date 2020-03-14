package com.laudoecia.api.worklistes;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.laudoecia.api.domain.Series;

@NamedQueries({
		@NamedQuery(name = SeriesQueryAttributes.FIND_BY_VIEW_ID_AND_SERIES_PK, query = "select a from SeriesQueryAttributes a where a.viewID = ?1 and a.series.pk = ?2"),
		@NamedQuery(name = SeriesQueryAttributes.DELETE_FOR_SERIES, query = "delete from SeriesQueryAttributes a where a.series = ?1"),
		@NamedQuery(name = SeriesQueryAttributes.VIEW_IDS_FOR_SERIES_PK, query = "select a.viewID from SeriesQueryAttributes a where a.series.pk = ?1") })
@Table(name = "series_query_attrs", uniqueConstraints = @UniqueConstraint(columnNames = { "view_id", "series_fk" }))
public class SeriesQueryAttributes {

	public static final String FIND_BY_VIEW_ID_AND_SERIES_PK = "SeriesQueryAttributes.findByViewIDAndSeriesPk";
	public static final String DELETE_FOR_SERIES = "SeriesQueryAttributes.deleteForSeries";
	public static final String VIEW_IDS_FOR_SERIES_PK = "SeriesQueryAttributes.viewIDsForSeriesPk";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk")
	private long pk;

	@Column(name = "view_id")
	private String viewID;

	@Column(name = "num_instances")
	private int numberOfInstances;

	@Column(name = "cuids_in_series")
	private String sopClassesInSeries;

	@Column(name = "retrieve_aets")
	private String retrieveAETs;

	@Column(name = "availability")
	private Availability availability;

	@ManyToOne(optional = false)
	@JoinColumn(name = "series_fk")
	private Series series;

	public long getPk() {
		return pk;
	}

	public String getViewID() {
		return viewID;
	}

	public void setViewID(String viewID) {
		this.viewID = viewID;
	}

	public int getNumberOfInstances() {
		return numberOfInstances;
	}

	public void setNumberOfInstances(int numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}

	public String getSOPClassesInSeries() {
		return sopClassesInSeries;
	}

	public void setSOPClassesInSeries(String sopClassesInSeries) {
		this.sopClassesInSeries = sopClassesInSeries;
	}

	public String getRetrieveAETs() {
		return retrieveAETs;
	}

	public void setRetrieveAETs(String retrieveAETs) {
		this.retrieveAETs = retrieveAETs;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
}
