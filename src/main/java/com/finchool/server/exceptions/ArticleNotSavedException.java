package com.finchool.server.exceptions;

public class ArticleNotSavedException extends RuntimeException{
    public ArticleNotSavedException(String message) {
        super(message);
    }
}
