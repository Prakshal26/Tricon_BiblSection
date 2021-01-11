package parser;

import database.PostgreConnect;
import handlers.BiblListHandlers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pojo.Country;

public class ElementParse {

    static void match(Element element, Country country, StringBuilder stringBuilder) {

        String tagName = element.getTagName();
        switch (tagName) {

            case "HEADING":
                country.setHeading(element.getTextContent());
                break;
            case "ALT-HEADING":
                country.setAltHeading(element.getTextContent());
                break;
            case "HEADING-NOTE":
                country.setHeadingNote(element.getTextContent());
                break;
            case "P":
                stringBuilder.append("<p>");
                BiblListHandlers.handleParagraph(element.getChildNodes(),stringBuilder);
                stringBuilder.append("</p>");
                break;
            case "BIBL-LIST":
                stringBuilder.append("<p>");
                BiblListHandlers.handleBiblList(element, country, stringBuilder);
                stringBuilder.append("</p>");
                break;
            case "BIBL-SECTION":
                match(element, country, stringBuilder);
                break;
            default:
                break;
        }
    }

    static void parseFiles(Document doc) {

        doc.getDocumentElement().normalize();
        Node entryNode = doc.getDocumentElement();

        Country country = new Country();

        NodeList nodeList = entryNode.getChildNodes();

        Element entryElement = (Element) entryNode;

        if (entryElement.hasAttribute("ISO")) {
            country.setCountryName(entryElement.getAttribute("ISO"));
        }
        if (entryElement.hasAttribute("ID")) {
            country.setXmlId((entryElement.getAttribute("ID")).toLowerCase());
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<nodeList.getLength();i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)nNode;
                ElementParse.match(element, country, stringBuilder);
            }
        }
        country.setDescription(stringBuilder.toString());
        PostgreConnect.insertCountry(country);
    }
}

