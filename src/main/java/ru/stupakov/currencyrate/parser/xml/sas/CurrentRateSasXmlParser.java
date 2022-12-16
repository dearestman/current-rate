package ru.stupakov.currencyrate.parser.xml.sas;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import ru.stupakov.currencyrate.model.CurrencyRate;
import ru.stupakov.currencyrate.parser.CurrencyRateParser;

import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stupakov D. L.
 **/
@Component
public class CurrentRateSasXmlParser implements CurrencyRateParser {

    @Override
    public List<CurrencyRate> parse(String pathToXmlFile) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<CurrencyRate> rates = new ArrayList<>();

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(pathToXmlFile));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all employees
        NodeList nList = document.getElementsByTagName("Valute");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eValute = (Element) node;
                rates.add(
                        new CurrencyRate(
                                eValute.getAttribute("ID"),
                                Integer.parseInt(eValute.getElementsByTagName("NumCode").item(0).getTextContent()),
                                eValute.getElementsByTagName("CharCode").item(0).getTextContent(),
                                Integer.parseInt(eValute.getElementsByTagName("Nominal").item(0).getTextContent()),
                                eValute.getElementsByTagName("Name").item(0).getTextContent(),
                                Double.parseDouble(eValute.getElementsByTagName("Value").item(0).getTextContent().replace(",", "."))
                        )
                );
            }
        }
        return rates;
    }
}
