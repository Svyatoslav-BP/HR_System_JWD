package com.epam.exception;

public class DaoException extends Exception{
    public DaoException(Exception e){
        e.printStackTrace();

    }
}
