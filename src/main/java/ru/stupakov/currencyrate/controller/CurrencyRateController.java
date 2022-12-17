package ru.stupakov.currencyrate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stupakov.currencyrate.dto.request.CurrencyRateRequest;
import ru.stupakov.currencyrate.dto.response.CurrencyRateResponse;
import ru.stupakov.currencyrate.exception.DateNotCorrectException;
import ru.stupakov.currencyrate.exception.NotFoundCurrencyRateException;
import ru.stupakov.currencyrate.exception.NotFoundXMLObject;
import ru.stupakov.currencyrate.exception.UserErrorResponse;
import ru.stupakov.currencyrate.service.CurrencyRateServiceImpl;

import java.util.zip.DataFormatException;

/**
 * @author Stupakov D. L.
 **/
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateServiceImpl currencyRateService;


    /**
     * Post запрос, который берет информацию с официального сайт центробанка рф по соотношению
     * рубля к конкретной валюте на конкретную дату: https://www.cbr.ru/scripts/XML_daily.asp
     * Тип запроса: POST
     * Url: /currency
     * @param request
     * тело запроса:
     * {
     *     "date" : "02/02/2020",
     *     "charName" : "EUR"
     * }
     * @return
     * Формат ответа:
     *{
     *     "charCode": "EUR",
     *     "nominal": 1,
     *     "name": "Евро",
     *     "value": 69.5976
     * }
     */
    @PostMapping()
    public CurrencyRateResponse getCurrency(@RequestBody CurrencyRateRequest request){
        return currencyRateService.getCurrentRateByDateAndCharName(request);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(DateNotCorrectException exception){
        UserErrorResponse userErrorResponse = new UserErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(NotFoundCurrencyRateException exception){
        UserErrorResponse userErrorResponse = new UserErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(NotFoundXMLObject exception){
        UserErrorResponse userErrorResponse = new UserErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
