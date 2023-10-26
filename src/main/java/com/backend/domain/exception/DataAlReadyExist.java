package com.backend.domain.exception;

public class DataAlReadyExist extends Exception {

    public DataAlReadyExist() {
        super();
    }

    public DataAlReadyExist(String message) {
        super(message);
    }

    public DataAlReadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAlReadyExist(Throwable cause) {
        super(cause);
    }

}
