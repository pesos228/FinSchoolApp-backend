package com.finchool.server.exceptions;

public class AchievementNotFoundException extends RuntimeException{
    public AchievementNotFoundException(String message) {
        super(message);
    }
}
