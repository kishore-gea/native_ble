/**
 * @author Jonas Hong - 320007906
 * @file NetworkListXmlParserHandlerTest
 * @brief NetworkListXmlParserHandler's unit test file
 * @date Jan/26/2016
 * Copyright (c) 2014 General Electric Corporation - Confidential - All rights reserved.
 */
package com.ge.commonframework.xmlParsers;

import com.ge.commonframework.dataModel.WifiNetworkInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.assertEquals;

public class NetworkListXmlParserHandlerTest {
    private static int index = 0;
    private SAXParser parser;
    private NetworkListXmlParserHandler handler;


    @Before
    public void setUp() throws Exception {
        index++;
        parser = SAXParserFactory.newInstance().newSAXParser();
        handler = new NetworkListXmlParserHandler();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
        handler = null;
    }

    @Test
    public void testStartElementWithOneNetwork() throws Exception {
        System.out.println(index + "] Testing testStartElementWithOneNetwork");
        String parsingText = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "    <commissioningNetworksAvailableUriType>" +
                "    <networks>" +
                "    <ssid><![CDATA[ssid1]]></ssid>" +
                "    <securityMode>WPA2_AES_PSK</securityMode>" +
                "    </networks>" +
                "    </commissioningNetworksAvailableUriType>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);

        ArrayList<WifiNetworkInfo> result = handler.getResult();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testStartElementWithTwoNetwork() throws Exception {
        System.out.println(index + "] Testing testStartElementWithTwoNetwork");
        String parsingText = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "    <commissioningNetworksAvailableUriType>" +
                "    <networks>" +
                "    <ssid><![CDATA[ssid1]]></ssid>" +
                "    <securityMode>WPA2_AES_PSK</securityMode>" +
                "    </networks>" +
                "    <networks>" +
                "    <ssid><![CDATA[ssid2]]></ssid>" +
                "    <securityMode>WPA2_AES_PSK</securityMode>" +
                "    </networks>" +
                "    </commissioningNetworksAvailableUriType>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);
        ArrayList<WifiNetworkInfo> result = handler.getResult();
        assertEquals(result.size(), 2);
    }

    @Test
    public void testStartElementWithZeroNetwork() throws Exception {
        System.out.println(index + "] Testing testStartElementWithZeroNetwork");
        String parsingText = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "    <commissioningNetworksAvailableUriType>" +
                "    </commissioningNetworksAvailableUriType>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);
        ArrayList<WifiNetworkInfo> result = handler.getResult();
        assertEquals(result.size(), 0);
    }

    @Test
    public void testEndElement() throws Exception {
        System.out.println(index + "] Testing testEndElement");

        String ssid = "tEsTsSiD";
        String securityMode = "WPA2_AES_PSK";

        String parsingText = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "    <commissioningNetworksAvailableUriType>" +
                "    <networks>" +
                "    <ssid><![CDATA[" + ssid + "]]></ssid>" +
                "    <securityMode>" + securityMode + "</securityMode>" +
                "    </networks>" +
                "    </commissioningNetworksAvailableUriType>";
        parser.parse(new InputSource(new StringReader(parsingText)), handler);
        ArrayList<WifiNetworkInfo> result = handler.getResult();

        String returnSsid = result.get(0).getSsid();
        assertEquals(ssid, returnSsid);

        String returnSecurityMode = result.get(0).getSecurityMode();
        assertEquals(securityMode, returnSecurityMode);
    }
}