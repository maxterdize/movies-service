package com.movies_service.movies_service_demo.exceptions;

public class RepositoryTransactionException extends RuntimeException {

    public RepositoryTransactionException() {}

    public RepositoryTransactionException(String message) {
        super(message);
    }

    public RepositoryTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryTransactionException(Throwable cause) {
        super(cause);
    }

    public RepositoryTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
