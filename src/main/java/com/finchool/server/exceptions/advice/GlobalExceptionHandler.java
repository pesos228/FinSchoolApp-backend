package com.finchool.server.exceptions.advice;

import com.finchool.server.exceptions.AchievementAlreadyExistsException;
import com.finchool.server.exceptions.AchievementNotFoundException;
import com.finchool.server.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AchievementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String userAlreadyExistsHandler(AchievementAlreadyExistsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AchievementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String achievementNotFoundException(AchievementNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundException(UserNotFoundException e){
        return e.getMessage();
    }
}
