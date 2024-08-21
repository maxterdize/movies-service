package com.movies_service.movies_service_demo.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException() {}

    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundException(Throwable cause) {
        super(cause);
    }

    public MovieNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
