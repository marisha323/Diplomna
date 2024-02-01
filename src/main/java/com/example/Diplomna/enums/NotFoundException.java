package com.example.Diplomna.enums;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {

    }
    public NotFoundException(String message) {
        super(message);
    }
}
