package com.inzeph.EmployeeManagement.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStorageException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(FileStorageException.class);

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}