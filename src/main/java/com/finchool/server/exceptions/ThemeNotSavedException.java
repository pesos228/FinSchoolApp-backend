package com.finchool.server.exceptions;

public class ThemeNotSavedException extends RuntimeException{
    public ThemeNotSavedException(String message) {
        super(message);
    }
}
