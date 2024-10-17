package com.finchool.server.exceptions;

public class ThemeNotFoundException extends RuntimeException{
    public ThemeNotFoundException(String message){
        super(message);
    }
}
