package com.cab302qut.java.Users;

/**
 * Specifies a specific user type.
 * @author Nicholas Bishop
 */
public enum UserType {
    /**
     * The regular end user of the trading program.
     */
    Default("Default"),

    /**
     * Specifies a specific user who can create/modify organisational units, users and/or items.
     */
    Administrator("Administrator");

    private final String label;

    UserType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }

    }
