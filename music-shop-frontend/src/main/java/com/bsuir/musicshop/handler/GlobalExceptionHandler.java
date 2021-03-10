package com.bsuir.musicshop.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handler(Model model, Exception ex) {

        model.addAttribute("message", ex.getMessage());
        return "exception";

    }
}
