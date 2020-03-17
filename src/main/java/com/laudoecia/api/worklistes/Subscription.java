package com.laudoecia.api.worklistes;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Subscription {

	public static final String ALL_IUID_AND_AET = "Subscription.allIUIDAndAET";
	public static final String AETS_BY_UPS = "Subscription.aetsByUPS";
	public static final String FIND_BY_UPS_AND_AET = "Subscription.findByUPSAndAET";
	public static final String DELETE_BY_AET = "Subscription.deleteByAET";
	public static final String DELETE_BY_IUID_AND_AET = "Subscription.deleteByIUIDAndAET";
	public static final String DELETE_BY_UPS = "Subscription.deleteByUPS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk")
	private long pk;

	@Basic(optional = false)
	@Column(name = "subscriber_aet", updatable = false)
	private String subscriberAET;

	@Basic(optional = false)
	@Column(name = "deletion_lock")
	private boolean deletionLock;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ups_fk")
	private UPS ups;

	public long getPk() {
		return pk;
	}

	public String getSubscriberAET() {
		return subscriberAET;
	}

	public void setSubscriberAET(String subscriberAET) {
		this.subscriberAET = subscriberAET;
	}

	public boolean isDeletionLock() {
		return deletionLock;
	}

	public void setDeletionLock(boolean deletionLock) {
		this.deletionLock = deletionLock;
	}

	public UPS getUPS() {
		return ups;
	}

	public void setUPS(UPS ups) {
		this.ups = ups;
	}

	@Override
	public String toString() {
		return "Subscription[pk=" + pk + ", ups=" + ups + ", aet=" + subscriberAET + ", deletionLock=" + deletionLock
				+ "]";
	}
}
