package ru.stupakov.currencyrate.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.stupakov.currencyrate.dto.request.CurrencyRateRequest;
import ru.stupakov.currencyrate.dto.response.CurrencyRateResponse;
import ru.stupakov.currencyrate.exception.NotFoundCurrencyRateException;
import ru.stupakov.currencyrate.model.CurrencyRate;
import ru.stupakov.currencyrate.parser.xml.sas.CurrencyRateDomXmlParserWithDateAndCharName;

import java.util.Optional;

/**
 * @author Stupakov D. L.
 **/
@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService{

    private final ModelMapper mapper;
    private final CurrencyRateDomXmlParserWithDateAndCharName parserXML;

    @Override
    public CurrencyRateResponse getCurrentRateByDateAndCharName(CurrencyRateRequest request) {


        Optional<CurrencyRate> currencyRate = parserXML.parseByDateAndCharName(request.getDate(), request.getCharName());

        if (currencyRate.isEmpty())
            throw new NotFoundCurrencyRateException("Error: " + "Currency rate with date = '" +
                    request.getDate() + "', charName = '" + request.getCharName() +"' not found");
        return convertToResponse(currencyRate.get());
    }

    private CurrencyRateResponse convertToResponse(CurrencyRate currencyRate){
        return mapper.map(currencyRate, CurrencyRateResponse.class);
    }
}
