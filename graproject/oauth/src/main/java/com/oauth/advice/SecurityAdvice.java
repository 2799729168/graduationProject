package com.oauth.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

/**
 * @author pb
 */
@ControllerAdvice
public class SecurityAdvice {
    @ExceptionHandler(AccessDeniedException.class)
    public String denied(){
        return "redirect:/login";
    }
}
