package ru.stupakov.currencyrate.exception;

/**
 * @author Stupakov D. L.
 **/
public class DateNotCorrectException extends RuntimeException{
    public DateNotCorrectException(String message) {
        super(message);
    }
}
