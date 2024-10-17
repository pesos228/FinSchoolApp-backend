package com.finchool.server.exceptions.advice;

import com.finchool.server.exceptions.*;
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

    @ResponseBody
    @ExceptionHandler(StorageFileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String storageFileNotFoundException(StorageFileNotFoundException e){return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String storageException(StorageException e){return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(GoalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String GoalNotFoundException(GoalNotFoundException e){return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(GoalUrlAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAlreadyExistsHandler(GoalUrlAlreadyExistsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String articleNotFoundException(ArticleNotFoundException e){return  e.getMessage();}

    @ResponseBody
    @ExceptionHandler(ThemeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String themeNotFoundException(ThemeNotFoundException e){return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(ThemeAlreadySavedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String themeAlreadySavedException(ThemeAlreadySavedException e){return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(ThemeNotSavedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String themeNotSavedException(ThemeNotSavedException e){return e.getMessage();}

}
