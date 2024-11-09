package com.finchool.server.exceptions;

public class ModuleNameOrDescriptionNotFoundException extends RuntimeException {
    public ModuleNameOrDescriptionNotFoundException(String message) {
        super(message);
    }
}
