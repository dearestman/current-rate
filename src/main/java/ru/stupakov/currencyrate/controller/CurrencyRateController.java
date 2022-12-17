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
