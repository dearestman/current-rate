package ru.stupakov.currencyrate.parser.xml.sas;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import ru.stupakov.currencyrate.config.CBConfig;
import ru.stupakov.currencyrate.exception.DateNotCorrectException;
import ru.stupakov.currencyrate.exception.NotFoundCurrencyRateException;
import ru.stupakov.currencyrate.exception.NotFoundXMLObject;
import ru.stupakov.currencyrate.model.CurrencyRate;
import ru.stupakov.currencyrate.parser.CurrencyRateParser;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author Stupakov D. L.
 **/
@Component
@RequiredArgsConstructor
public class CurrencyRateDomXmlParserWithDateAndCharName implements CurrencyRateParser {

    private final CBConfig cbConfig;

    @Override
    public Optional<CurrencyRate> parseByDateAndCharName(String date, String charName)  {
        Optional<CurrencyRate> rate = Optional.empty();

        if (!Pattern.matches("^([0-9]{2}/[0-9]{2}/[0-9]{4})$", date)) {
           throw new DateNotCorrectException("Error: Date " + date + " has incorrect format. It should be '00/00/0000'");
        }

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        //Build Document
        String url = cbConfig.getUrl()+"?date_req="+date;
        Document document = null;
        try {
            document = Objects.requireNonNull(builder).parse(new URL(url).openStream());
        } catch (SAXException | IOException e) {
            throw new NotFoundCurrencyRateException("Error: Url = '" + url + "' incorrect.");
        }

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //filter with charCode = USD
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile("ValCurs/Valute[CharCode='"+charName+"']");
        } catch (XPathExpressionException e) {
            throw new NotFoundXMLObject("Error: XML object 'ValCurs/Valute[CharCode='"+charName+"']' not found");
        }

        NodeList nList = null;
        try {
            nList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new NotFoundCurrencyRateException("Error: XML file with url = '" + url + "' incorrect.");
        }

        for (int temp = 0; temp < Objects.requireNonNull(nList).getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eValute = (Element) node;
                rate =
                        Optional.of(new CurrencyRate(
                                eValute.getAttribute("ID"),
                                Integer.parseInt(eValute.getElementsByTagName("NumCode").item(0).getTextContent()),
                                eValute.getElementsByTagName("CharCode").item(0).getTextContent(),
                                Integer.parseInt(eValute.getElementsByTagName("Nominal").item(0).getTextContent()),
                                eValute.getElementsByTagName("Name").item(0).getTextContent(),
                                Double.parseDouble(eValute.getElementsByTagName("Value").item(0).getTextContent().replace(",", "."))
                        ));
            }
        }
        return rate;
    }
}
