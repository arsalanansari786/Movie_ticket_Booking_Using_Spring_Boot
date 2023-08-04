package com.gfg.shoutreview.Exception;

public class NotFoundException extends RuntimeException{
    private final String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
