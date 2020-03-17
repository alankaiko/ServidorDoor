package com.laudoecia.api.worklistes;

public enum UPSState {
    SCHEDULED,
    IN_PROGRESS,
    CANCELED,
    COMPLETED;

    @Override
    public String toString() {
        return name().replace('_', ' ');
    }

    public static UPSState fromString(String code) {
        return valueOf(code.replace(' ', '_'));
    }
}
