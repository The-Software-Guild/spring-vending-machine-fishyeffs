package com.mthree.nick.vending.dao;

public class InvalidFileFormat extends Exception{
    public InvalidFileFormat(String message) {
        super(message);
    }

    public InvalidFileFormat(String message, Throwable cause) {
        super(message, cause);
    }
}
