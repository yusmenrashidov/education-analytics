package com.education.analytics.model.exception;

public class StudentResultNotFoundException extends RuntimeException {

    public StudentResultNotFoundException(String message){
        super(message);
    }

    public StudentResultNotFoundException(){
        super();
    }

}
