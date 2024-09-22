package com.bms.exception;

public class TransactionnException extends Exception {
    private static final long serialVersionUID = 1L;

    public TransactionnException() {
        super();
    }

    public TransactionnException(String message) {
        super(message);
    }

    public TransactionnException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionnException(Throwable cause) {
        super(cause);
    }
}
