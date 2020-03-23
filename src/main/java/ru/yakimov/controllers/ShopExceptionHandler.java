package ru.yakimov.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.yakimov.exceptions.InternalServerErrorException;
import ru.yakimov.exceptions.ProductNotFoundException;

@Slf4j
@ControllerAdvice
public class ShopExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(final ProductNotFoundException ex) {
        log.error("Product not found thrown", ex);
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public String internalServerErrorException(final InternalServerErrorException ex) {
        log.error("Internal server error", ex);
        return "error";
    }

}