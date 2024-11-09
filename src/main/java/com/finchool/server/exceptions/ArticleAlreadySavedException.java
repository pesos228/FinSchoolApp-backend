package com.finchool.server.exceptions;

public class ArticleAlreadySavedException extends RuntimeException{
    public ArticleAlreadySavedException(String message) {
        super(message);
    }
}
