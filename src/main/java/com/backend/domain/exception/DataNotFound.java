package com.backend.domain.exception;

public class DataNotFound extends Exception {

    public DataNotFound() {
        super();
    }

    public DataNotFound(String message) {
        super(message);
    }

    public DataNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFound(Throwable cause) {
        super(cause);
    }

}
