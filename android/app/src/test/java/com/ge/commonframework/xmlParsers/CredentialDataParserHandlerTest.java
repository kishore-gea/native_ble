/**
 * @author Jonas Hong - 320007906
 * @file CredentialDataParserHandlerTest
 * @brief CredentialDataParserHandlerTest's unit test file
 * @date Jan/26/2016
 * Copyright (c) 2014 General Electric Corporation - Confidential - All rights reserved.
 */

package com.ge.commonframework.xmlParsers;

import com.ge.commonframework.dataModel.CredentialData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static org.junit.Assert.assertEquals;

public class CredentialDataParserHandlerTest {
    private static int index = 0;
    private SAXParser parser;
    private XMPPCredentialParserHandler handler;

    @Before
    public void setUp() throws Exception {
        index++;
        parser = SAXParserFactory.newInstance().newSAXParser();
        handler = new XMPPCredentialParserHandler();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
        handler = null;
    }

    @Test
    public void testParsing() throws Exception {
        System.out.println(index + "] Testing testParsing");
        String jid = "testtesttest_testtesttesttes@xmpp1.fld.wca.geappliances.com";
        String jidpassword = "SomeRandomJidPassword";
        String xmppaddress = "xmpp1.fld.wca.geappliances.com";
        String xmppport = "5225";


        String parsingText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<mobilexmppcredentialsresponse>\n" +
                "<jid>" + jid + "</jid>\n" +
                "<jidpassword>" + jidpassword + "</jidpassword>\n" +
                "<xmppaddress>" + xmppaddress + "</xmppaddress>\n" +
                "<xmppport>" + xmppport + "</xmppport>\n" +
                "</mobilexmppcredentialsresponse>";

        parser.parse(new InputSource(new StringReader(parsingText)), handler);
        CredentialData result = handler.getResult();

        String returnJid = result.getJid();
        assertEquals(jid, returnJid);

        String returnJidPassword = result.getJidPassword();
        assertEquals(jidpassword, returnJidPassword);

        String returnXmppAddress = result.getXmppAddress();
        assertEquals(xmppaddress, returnXmppAddress);

        String returnXmppPort = result.getXmppPort();
        assertEquals(xmppport, returnXmppPort);

    }

}