/**
 * @author Jonas Hong - 320007906
 * @file PreferencesXmlParserHandlerTest
 * @brief PreferencesXmlParserHandlerTest's unit test file
 * @date Jan/26/2016
 * Copyright (c) 2014 General Electric Corporation - Confidential - All rights reserved.
 */
package com.ge.commonframework.xmlParsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.assertEquals;

public class PreferencesXmlParserHandlerTest {
    private static int index = 0;
    private SAXParser parser;
    private PreferencesXmlParserHandler handler;


    @Before
    public void setUp() throws Exception {
        index++;
        parser = SAXParserFactory.newInstance().newSAXParser();
        handler = new PreferencesXmlParserHandler();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
        handler = null;
    }

    @Test
     public void testStartElementWithOnePreference() throws Exception {
        System.out.println(index + "] Testing testStartElementWithOnePreference");
        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<preferences>  \n" +
                "<preference name=\"home.clocksync\">true</preference>\n" +
                "</preferences>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<String> result = handler.getResult();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testStartElementWithTwoPreference() throws Exception {
        System.out.println(index + "] Testing testStartElementWithTwoPreference");
        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<preferences>  \n" +
                "<preference name=\"home.clocksync\">true</preference>\n" +
                "<preference name=\"home.timezone\">Pacific/Honolulu</preference>\n" +
                "</preferences>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<String> result = handler.getResult();
        assertEquals(result.size(), 2);
    }

    @Test
    public void testStartElementWithZeroPreference() throws Exception {
        System.out.println(index + "] Testing testStartElementWithZeroPreference");
        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<preferences>  \n" +
                "</preferences>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<String> result = handler.getResult();
        assertEquals(result.size(), 0);
    }

    @Test
    public void testEndElement() throws Exception {
        System.out.println(index + "] Testing testEndElement");
        String name = "home.clocksync";
        String data = "true";

        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<preferences>  \n" +
                "<preference name=\"" + name+ "\">" + data + "</preference>\n" +
                "</preferences>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);
        ArrayList<String> result = handler.getResult();

        String[] stringList = result.get(0).split("-");

        assertEquals(name, stringList[0]);
        assertEquals(data, stringList[1]);
    }

}