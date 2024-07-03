package com.example.webcrawler.exceptions;

import com.example.webcrawler.utils.ErrorType;

import java.util.Date;
import java.util.List;

public record ErrorDetails(Date timestamp, ErrorType type, List<Error> errors) {
}
