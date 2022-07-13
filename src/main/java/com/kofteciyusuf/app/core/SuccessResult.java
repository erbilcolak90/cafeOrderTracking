package com.kofteciyusuf.app.core;

import org.springframework.http.HttpStatus;

public class SuccessResult extends Result{

    public SuccessResult(String message) {
        super(true, message);
    }

    public SuccessResult() {
        super(true);
    }
}
