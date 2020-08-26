package com.nfq.task.domain;

public enum VisitStatus {
    CREATED("Created"),
    STARTED("Started"),
    ENDED("Ended"),
    CANCELLED("Cancelled")
    ;

    private final String name;

    VisitStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
