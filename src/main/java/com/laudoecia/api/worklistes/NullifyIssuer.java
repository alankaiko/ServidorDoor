package com.laudoecia.api.worklistes;

import java.util.stream.Stream;

import org.dcm4che3.data.Issuer;

public enum NullifyIssuer {
	ALWAYS {
		public boolean test(Issuer issuer, Issuer[] others) {
			return issuer != null;
		}
	},
	MATCHING {
		@Override
		public boolean test(Issuer issuer, Issuer[] others) {
			return issuer != null && Stream.of(others).anyMatch(issuer::matches);
		}
	},
	NOT_MATCHING {
		@Override
		public boolean test(Issuer issuer, Issuer[] others) {
			return issuer != null && Stream.of(others).noneMatch(issuer::matches);
		}
	};

	public abstract boolean test(Issuer issuer, Issuer[] others);
}
