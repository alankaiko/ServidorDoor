package com.laudoecia.api.worklistes;

import java.io.Serializable;

public class StoreDuplicate implements Serializable {

	private static final long serialVersionUID = 3499899064498849214L;

	public enum Action {
		IGNORE, STORE, REPLACE,
	}

	public enum Condition {
		NO_FILE {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return noFiles;
			}
		},
		EQ_CHECKSUM {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && eqChecksum;
			}
		},
		NE_CHECKSUM {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && !eqChecksum;
			}
		},
		EQ_FSGROUP {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && eqFsGroup;
			}
		},
		NE_FSGROUP {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && !eqFsGroup;
			}
		},
		EQ_CHECKSUM_AND_EQ_FSGROUP {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && eqChecksum && eqFsGroup;
			}
		},
		EQ_CHECKSUM_AND_NE_FSGROUP {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && eqChecksum && !eqFsGroup;
			}
		},
		NE_CHECKSUM_AND_EQ_FSGROUP {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && !eqChecksum && eqFsGroup;
			}
		},
		NE_CHECKSUM_AND_NE_FSGROUP {
			@Override
			public boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup) {
				return !noFiles && !eqChecksum && eqFsGroup;
			}
		};

		public abstract boolean matches(boolean noFiles, boolean eqChecksum, boolean eqFsGroup);
	}

	private final Condition condition;
	private final Action action;

	public StoreDuplicate(Condition condition, Action action) {
		this.condition = condition;
		this.action = action;
	}

	public final Condition getCondition() {
		return condition;
	}

	public final Action getAction() {
		return action;
	}

}
