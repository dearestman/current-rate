package ru.stupakov.currencyrate.exception;

/**
 * @author Stupakov D. L.
 **/
public class NotFoundCurrencyRateException extends RuntimeException{
    public NotFoundCurrencyRateException(String message) {
        super(message);
    }
}
