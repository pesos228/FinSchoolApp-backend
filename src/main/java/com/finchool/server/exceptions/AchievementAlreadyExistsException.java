package com.finchool.server.exceptions;

public class AchievementAlreadyExistsException extends RuntimeException{
    public AchievementAlreadyExistsException(String message) {
        super(message);
    }
}
