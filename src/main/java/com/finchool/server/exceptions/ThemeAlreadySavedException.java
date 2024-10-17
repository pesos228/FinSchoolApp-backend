package com.finchool.server.exceptions;

public class ThemeAlreadySavedException extends RuntimeException{
    public ThemeAlreadySavedException(String message) {
        super(message);
    }
}
