package com.doctor.exception;

public class FileStorageException extends RuntimeException{
    public FileStorageException(String fileName ){
        super(fileName);
    }

}
