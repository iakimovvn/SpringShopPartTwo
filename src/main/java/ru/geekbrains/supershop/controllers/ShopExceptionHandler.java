package ru.geekbrains.supershop.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.geekbrains.supershop.exceptions.ProductNotFoundException;
import ru.geekbrains.supershop.exceptions.UnsupportedMediaTypeException;

@Slf4j
@ControllerAdvice
public class ShopExceptionHandler {

    // TODO ДЗ - оформить страницу ошибки 404

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(final ProductNotFoundException ex) {
        log.error("Product not found exception thrown", ex);
        return "error";
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(UnsupportedMediaTypeException.class)
    public String handleUnsupportedMediaTypeException(final UnsupportedMediaTypeException ex) {
        log.error("Unsupported format file ", ex);
        return "error";
    }

}