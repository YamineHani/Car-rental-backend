package com.carrental.carrental.model.enums;

public enum Method {
    CASH("CASH"),
    CREDIT("CREDIT");

    private final String displayName;

    Method(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
