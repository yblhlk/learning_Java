package com.Exception;

public class PasswordNotMatchException extends Exception{
    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String m) {
        super(m);
    }
}