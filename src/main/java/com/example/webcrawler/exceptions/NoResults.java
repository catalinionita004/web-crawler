package com.example.webcrawler.exceptions;

public class NoResults extends RuntimeException{
    public NoResults(String errorMessage){
        super(errorMessage);
    }
}
