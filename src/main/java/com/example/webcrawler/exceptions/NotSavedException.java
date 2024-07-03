package com.example.webcrawler.exceptions;

public class NotSavedException extends  RuntimeException{
    public NotSavedException(String errorMessage){
        super(errorMessage);
    }
}
