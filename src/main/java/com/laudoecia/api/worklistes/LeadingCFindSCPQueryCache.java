package com.laudoecia.api.worklistes;

import java.util.Objects;

import org.dcm4che3.data.Attributes;

public class LeadingCFindSCPQueryCache extends Cache<LeadingCFindSCPQueryCache.Key, Attributes> {

	public static class Key {
		public final String cfindSCP;
		public final String studyInstanceUID;

		public Key(String cfindSCP, String studyInstanceUID) {
			this.cfindSCP = cfindSCP;
			this.studyInstanceUID = studyInstanceUID;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Key key = (Key) o;
			return Objects.equals(cfindSCP, key.cfindSCP) && Objects.equals(studyInstanceUID, key.studyInstanceUID);
		}

		@Override
		public int hashCode() {
			return Objects.hash(cfindSCP, studyInstanceUID);
		}
	}
}
