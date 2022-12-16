package ru.stupakov.currencyrate.parser.xml.sas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Stupakov D. L.
 **/
@SpringBootTest
class CurrentRateSasXmlParserTest {

    @Autowired
    private CurrentRateSasXmlParser currentRateSasXmlParser;

    @Test
    public void testDomXMLParse() throws ParserConfigurationException, IOException, SAXException {
        System.out.println(currentRateSasXmlParser.parse("XML_daily.asp.xml"));

    }



}