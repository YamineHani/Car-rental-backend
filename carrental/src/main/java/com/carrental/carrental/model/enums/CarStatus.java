package com.carrental.carrental.model.enums;

public enum CarStatus {
    ACTIVE("AIRPORT"),
    RENTED("DOWNTOWN"),
    OUTOFSERVICE("OUT OF SERVICE");

    private final String displayName;

    CarStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
