package com.example.webcrawler.exceptions;


public record Error(String fieldName, String wrongValue, String message,String otherDetails) {
}
