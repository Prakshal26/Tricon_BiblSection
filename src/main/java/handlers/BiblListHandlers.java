package handlers;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pojo.Country;



public class BiblListHandlers {


    public static void handleParagraph(NodeList nodeList, StringBuilder stringBuilder) {

        for (int i=0;i<nodeList.getLength();i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType()==Node.TEXT_NODE) {
                stringBuilder.append(node.getNodeValue().trim());
            }
            if (node.getNodeType()==Node.ELEMENT_NODE) {
                Element subElement = (Element) node;
                if (subElement.getTagName().equalsIgnoreCase("I")) {
                    stringBuilder.append("<i> ");
                    stringBuilder.append(subElement.getTextContent());
                    stringBuilder.append("</i>");
                }
                if (subElement.getTagName().equalsIgnoreCase("B")) {
                    stringBuilder.append("<b> ");
                    stringBuilder.append(subElement.getTextContent());
                    stringBuilder.append("</b>");
                }
                if (subElement.getTagName().equalsIgnoreCase("XR")) {
                    if (subElement.hasAttribute("REF")) {
                        stringBuilder.append(" ");
                        stringBuilder.append("<a href =\"https://www.europaworld.com/entry/");
                        stringBuilder.append((subElement.getAttribute("REF")).toLowerCase());
                        stringBuilder.append("\">");
                        stringBuilder.append(subElement.getTextContent());
                        stringBuilder.append("</a>");
                    }
                }
                if (subElement.getTagName().equalsIgnoreCase("SC")) {
                    stringBuilder.append("<span style=\"font-variant:small-caps; font-weight:bold\">");
                    stringBuilder.append(" ");
                    stringBuilder.append(subElement.getTextContent());
                    stringBuilder.append("</span>");
                }
                if (subElement.hasChildNodes() && subElement.getTagName()!="B" &&
                        subElement.getTagName()!="I" &&
                        subElement.getTagName()!="XR" &&
                        subElement.getTagName()!="SC") {
                    handleParagraph(subElement.getChildNodes(),stringBuilder);
                }
            }
        }
    }


    public static void handleBiblList(Element element, Country country, StringBuilder stringBuilder) {

        NodeList nodeList = element.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element subElement = (Element) nNode;
                if (subElement.getTagName().equalsIgnoreCase("BIBL-ENTRY")) {
                    if (subElement.hasAttribute("TYPE")) {
                        if (subElement.getAttribute("TYPE").equalsIgnoreCase("IMPLIED-AUTHOR")) {
                            stringBuilder.append("——");
                        }
                    }
                    handleParagraph(subElement.getChildNodes(), stringBuilder);
                    stringBuilder.append("<br>");
                }
                if (subElement.getTagName().equalsIgnoreCase("P")) {
                    stringBuilder.append("<p>");
                    handleParagraph(subElement.getChildNodes(), stringBuilder);
                    stringBuilder.append("</p>");
                }
            }
        }
    }
}