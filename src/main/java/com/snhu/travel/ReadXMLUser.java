package com.snhu.travel;
//my variation based on ReadXMLFile

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLUser {
    public static void main(String argv[]) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean buname = false;
                boolean bpass = false;

                public void startElement(String uri, String localName,String qName, 
                            Attributes attributes) throws SAXException {

                    System.out.println("Start Element :" + qName);

                    if (qName.equalsIgnoreCase("USERNAME")) {
                        buname = true;
                    }

                    if (qName.equalsIgnoreCase("PASSWORD")) {
                        bpass = true;
                    }

                }

                public void endElement(String uri, String localName,
                    String qName) throws SAXException {

                    System.out.println("End Element :" + qName);

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (buname) {
                        System.out.println("First Name : " + new String(ch, start, length));
                        buname = false;
                    }

                    if (bpass) {
                        System.out.println("Last Name : " + new String(ch, start, length));
                        bpass = false;
                    }

                }

            };

            saxParser.parse("c:\\file.xml", handler);

        } catch (Exception e) {
           e.printStackTrace();
        }

    }

}