package com.finchool.server.exceptions;

public class GoalUrlAlreadyExistsException extends RuntimeException{
    public GoalUrlAlreadyExistsException(String message) {
        super(message);
    }
}
