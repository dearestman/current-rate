package ru.stupakov.currencyrate.dto.response;

import lombok.Data;

/**
 * @author Stupakov D. L.
 **/
@Data
public class CurrencyRateResponse {
    private String charCode;
    private int nominal;
    private String name;
    private double value;
}
