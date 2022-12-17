package ru.stupakov.currencyrate.parser;

import lombok.SneakyThrows;
import org.xml.sax.SAXException;
import ru.stupakov.currencyrate.model.CurrencyRate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Stupakov D. L.
 **/
public interface CurrencyRateParser {
    Optional<CurrencyRate> parseByDateAndCharName(String date, String charName);
}
