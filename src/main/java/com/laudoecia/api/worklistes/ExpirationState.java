package com.laudoecia.api.worklistes;

public enum ExpirationState {
	UPDATEABLE, // 0
	FROZEN, // 1
	REJECTED, // 2
	EXPORT_SCHEDULED, // 3
	FAILED_TO_EXPORT, // 4
	FAILED_TO_REJECT // 5
}
