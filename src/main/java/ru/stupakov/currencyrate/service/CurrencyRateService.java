package ru.stupakov.currencyrate.service;

import ru.stupakov.currencyrate.dto.request.CurrencyRateRequest;
import ru.stupakov.currencyrate.dto.response.CurrencyRateResponse;

/**
 * @author Stupakov D. L.
 **/
public interface CurrencyRateService {
    CurrencyRateResponse getCurrentRateByDateAndCharName(CurrencyRateRequest request);
}
