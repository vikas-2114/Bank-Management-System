package com.bms.exception;

public class AdminException extends Exception {
    // Constructor that accepts a message
    public AdminException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public AdminException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
