package com.kofteciyusuf.app.core;

import org.springframework.http.HttpStatus;

public class ErrorResult extends Result{

    public ErrorResult(String message) {
        super(false, message);
    }

    public ErrorResult() {
        super(false);
    }
}
