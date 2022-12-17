package ru.stupakov.currencyrate.dto.request;

import lombok.Data;

/**
 * @author Stupakov D. L.
 **/
@Data
public class CurrencyRateRequest {
    private String date;
    private String charName;
}
