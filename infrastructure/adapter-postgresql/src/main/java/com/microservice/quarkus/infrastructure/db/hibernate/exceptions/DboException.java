package com.microservice.quarkus.infrastructure.db.hibernate.exceptions;

public class DboException extends RuntimeException {
    public DboException(String message) {
        super(message);
    }

    public DboException(String format, Object... objects) {
        super(String.format(format, objects));
    }
}
