package com.finchool.server.exceptions;

public class ModuleNotFoundException extends RuntimeException{
    public ModuleNotFoundException(String message){
        super(message);
    }
}
