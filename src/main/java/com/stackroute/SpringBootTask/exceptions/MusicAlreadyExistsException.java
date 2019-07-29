package com.stackroute.SpringBootTask.exceptions;

public class MusicAlreadyExistsException extends Exception {
    public MusicAlreadyExistsException(String message)
    {
        super(message);
    }
}
