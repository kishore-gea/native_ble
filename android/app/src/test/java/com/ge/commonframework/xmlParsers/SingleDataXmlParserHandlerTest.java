/**
 * @author Jonas Hong - 320007906
 * @file SingleDataXmlParserHandlerTest
 * @brief SingleDataXmlParserHandlerTest's unit test
 * @date Jan/26/2016
 * Copyright (c) 2014 General Electric Corporation - Confidential - All rights reserved.
 */

package com.ge.commonframework.xmlParsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.*;


public class SingleDataXmlParserHandlerTest {
    private static int index = 0;
    private SAXParser parser;
    private SingleDataXmlParserHandler handler;

    @Before
    public void setUp() throws Exception {
        index++;
        parser = SAXParserFactory.newInstance().newSAXParser();
        handler = new SingleDataXmlParserHandler();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
        handler = null;
    }

    @Test
    public void testParsing() throws Exception {
        System.out.println(index + "] Testing testParsing");

        String firstWord = "firstWord";
        String secondWord = "secondWord";

        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<testuri>\n" +
                "<" + SingleDataXmlParserHandler.MODEL_VALIDATE + ">" + firstWord + "</" + SingleDataXmlParserHandler.MODEL_VALIDATE + ">\n" +
                "<" + SingleDataXmlParserHandler.MAC_ID + ">" + secondWord + "</" + SingleDataXmlParserHandler.MAC_ID + ">\n" +
                "</testuri>";

        handler.setTag(SingleDataXmlParserHandler.MODEL_VALIDATE);
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        String result = handler.getResult();
        assertEquals(firstWord, result);

        handler.setTag(SingleDataXmlParserHandler.MAC_ID);
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        result = handler.getResult();
        assertEquals(secondWord, result);
    }

    @Test
    public void testParsingNull() throws Exception {
        System.out.println(index + "] Testing testParsingNull");

        String firstWord = "firstWord";

        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<testuri>\n" +
                "<" + SingleDataXmlParserHandler.MODEL_VALIDATE + ">" + firstWord + "</" + SingleDataXmlParserHandler.MODEL_VALIDATE + ">\n" +
                "</testuri>";

        handler.setTag(SingleDataXmlParserHandler.MAC_ID);
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        String result = handler.getResult();
        assertNull(result);
    }
}