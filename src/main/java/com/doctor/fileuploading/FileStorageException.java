package com.doctor.fileuploading;

public class FileStorageException extends RuntimeException{
    public FileStorageException(String fileName){
        super(fileName);

    }
}
