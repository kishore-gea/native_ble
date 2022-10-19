package com.ge.commonframework.xmlParsers;

import com.ge.commonframework.https.jsonstructure.notification.NotificationData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.*;

/**
 * @author Jonas Hong - 320007906
 * @file NotificationByProductlineXmlParserHandlerTest
 * @brief simple brief
 * @date Jan/26/2016
 * Copyright (c) 2014 General Electric Corporation - Confidential - All rights reserved.
 */
public class NotificationByProductlineXmlParserHandlerTest {
    private static int index = 0;
    private SAXParser parser;
    private NotificationByProductlineXmlParserHandler handler;

    @Before
    public void setUp() throws Exception {
        index++;
        parser = SAXParserFactory.newInstance().newSAXParser();
        handler = new NotificationByProductlineXmlParserHandler();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
        handler = null;
    }

    @Test
    public void testStartElementWithOneNotification() throws Exception {
        System.out.println(index + "] Testing testStartElementWithOneNotification");
        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><notifications>\n" +
                "<notification>\n" +
                "<sequence>26</sequence>\n" +
                "<name>appliance.{jid}.notification.hotwater</name>\n" +
                "<displayname>Hot Water</displayname>\n" +
                "<description>Notifications related to the hot water dispensing feature of the refrigerator.  If your refrigerator does not have these capabilities, you will not get this alert.</description>\n" +
                "<defaultvalue>1</defaultvalue>\n" +
                "</notification>  \n" +
                "</notifications>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<NotificationData> result = handler.getResult();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testStartElementWithTwoNotification() throws Exception {
        System.out.println(index + "] Testing testStartElementWithTwoNotification");
        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><notifications>\n" +
                "<notification>\n" +
                "<sequence>26</sequence>\n" +
                "<name>appliance.{jid}.notification.hotwater</name>\n" +
                "<displayname>Hot Water</displayname>\n" +
                "<description>Notifications related to the hot water dispensing feature of the refrigerator.  If your refrigerator does not have these capabilities, you will not get this alert.</description>\n" +
                "<defaultvalue>1</defaultvalue>\n" +
                "</notification>  \n" +
                "<notification>\n" +
                "<sequence>25</sequence>\n" +
                "<name>appliance.{jid}.notification.powertemp</name>\n" +
                "<displayname>Power Outage Temperature Alerts</displayname>\n" +
                "<description>Get an alert if there is a power outage in your home and fresh food and/or freezer temps are out of the normal operating range when power returns.</description>\n" +
                "<defaultvalue>1</defaultvalue> \n" +
                "</notification>\n" +
                "</notifications>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<NotificationData> result = handler.getResult();
        assertEquals(result.size(), 2);
    }

    @Test
    public void testStartElementWithZeroNotification() throws Exception {
        System.out.println(index + "] Testing testStartElementWithZeroNotification");
        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><notifications>\n" +
                "</notifications>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<NotificationData> result = handler.getResult();
        assertEquals(result.size(), 0);
    }

    @Test
    public void testEndElement() throws Exception {
        System.out.println(index + "] testEndElement");
        String name = "appliance.{jid}.notification.hotwater";
        String displayName = "Hot Water";
        String description = "Notifications related to the hot water dispensing feature of the refrigerator.  If your refrigerator does not have these capabilities, you will not get this alert.";
        String defaultvalue = "1";

        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><notifications>\n" +
                "<notification>\n" +
                "<sequence>26</sequence>\n" +
                "<name>" + name + "</name>\n" +
                "<displayname>" + displayName + "</displayname>\n" +
                "<description>" + description + "</description>\n" +
                "<defaultvalue>" + defaultvalue + "</defaultvalue>\n" +
                "</notification>  \n" +
                "</notifications>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<NotificationData> result = handler.getResult();

        String returnName = result.get(0).getName();
        assertEquals(name, returnName);

        String returnDisplayName = result.get(0).getDisplayName();
        assertEquals(displayName, returnDisplayName);

        String returnDescription = result.get(0).getDescription();
        assertEquals(description, returnDescription);

        Boolean returnDefaultValue = result.get(0).getDefaultValue();
        assertEquals(Boolean.TRUE, returnDefaultValue);
    }

    @Test
    public void testEndElementDefaultvalue() throws Exception {
        System.out.println(index + "] testEndElementDefaultvalue");
        String name = "appliance.{jid}.notification.hotwater";
        String displayName = "Hot Water";
        String description = "Notifications related to the hot water dispensing feature of the refrigerator.  If your refrigerator does not have these capabilities, you will not get this alert.";
        String defaultvalue = "0";

        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><notifications>\n" +
                "<notification>\n" +
                "<sequence>26</sequence>\n" +
                "<name>" + name + "</name>\n" +
                "<displayname>" + displayName + "</displayname>\n" +
                "<description>" + description + "</description>\n" +
                "<defaultvalue>" + defaultvalue + "</defaultvalue>\n" +
                "</notification>  \n" +
                "</notifications>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<NotificationData> result = handler.getResult();

        Boolean returnDefaultValue = result.get(0).getDefaultValue();
        assertEquals(Boolean.FALSE, returnDefaultValue);
    }
}