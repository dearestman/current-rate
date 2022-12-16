package ru.stupakov.currencyrate.parser;

import org.xml.sax.SAXException;
import ru.stupakov.currencyrate.model.CurrencyRate;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * @author Stupakov D. L.
 **/
public interface CurrencyRateParser {
    List<CurrencyRate> parse(String ratesAsString) throws ParserConfigurationException, IOException, SAXException;

}
