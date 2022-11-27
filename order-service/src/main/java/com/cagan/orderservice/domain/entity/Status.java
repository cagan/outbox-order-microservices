package com.cagan.orderservice.domain.entity;

public enum Status {
    CREATED(0), CANCELED(1), COMPLETED(2), IN_PROGRESS(3);

    public final int status;

    Status(int status) {
        this.status = status;
    }
}
