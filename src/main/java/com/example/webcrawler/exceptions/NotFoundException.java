package com.example.webcrawler.exceptions;

import com.example.webcrawler.utils.ErrorType;

import java.util.List;

public class NotFoundException extends  RuntimeException{

    private List<Error> errorList;
    private ErrorType errorType;

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }

    public NotFoundException(String errorMessage){
        super(errorMessage);
    }

    public NotFoundException(String errorMessage,List<Error> errorList,ErrorType errorType){
        super(errorMessage);
        setErrorList(errorList);
        setErrorType(errorType);
    }


}
