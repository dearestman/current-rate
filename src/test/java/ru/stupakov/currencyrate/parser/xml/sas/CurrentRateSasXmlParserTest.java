package ru.stupakov.currencyrate.parser.xml.sas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * @author Stupakov D. L.
 **/
@SpringBootTest
class CurrentRateSasXmlParserTest {

    @Autowired
    private CurrencyRateDomXmlParserWithDateAndCharName currentRateSasXmlParser;

    @Test
    public void testDomXMLParse() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        System.out.println(currentRateSasXmlParser.parseByDateAndCharName( "02/03/2002", "USD"));
    }



}