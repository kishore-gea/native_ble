/*
 * Copyright GE Appliances, a Haier Company (Confidential). All rights reserved.
 */

package com.ge.commonframework.appliances

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

/**
 * Examples taken from [Confluence][https://geappliances.atlassian.net/wiki/spaces/WCA/pages/215026016/XMPP+JID+Format]
 * CAT1 - d01234567890_CPHx1889I5176Yb@xmpp1.wca.geappliances.com/anything1
 * CAT2 - dABCDEF01234_CPHx1889I5176Yb@xmpp1.wca.geappliances.com/anything2
 * MOBILE1 - lijss9302li301oe_CPHx1889I5176Yb@xmpp1.wca.geappliances.com/anything3
 */
class JidTest {

    private val cat1Mac = "d01234567890"
    private val cat2Mac = "dABCDEF01234"
    private val mobileMac = "lijss9302li301oe"
    private val home = "CPHx1889I5176Yb"
    private val home2 = "a87VDbkl23BA133"
    private val server = "xmpp1.wca.geappliances.com"
    private val server2 = "xmpp2.wca.geappliances.com"
    private val resourcePath1 = "anything1/and/another/thing"
    private val resourcePath2 = "anything2"
    private val resourcePath3 = "anything3"

    private val invalidId1 = "stringWithNoUnderscore"
    private val invalidId2 = "someOtherStringWithNoUnderscore"
    private val invalidId3 = "anotherOne"
    private val invalidIds = listOf(
            invalidId1, invalidId2, invalidId3
    )
    private val invalidJidStrings = listOf(
            "$invalidId1@$server/$resourcePath1",
            "$invalidId2@$server2/",
            "$invalidId3@$server",
            invalidId3
    )

    private data class TestJid(
            val mac: String,
            val home: String,
            val server: String,
            val res: String
    )

    private val cat1 = TestJid(cat1Mac, home, server, resourcePath1)
    private val cat2 = TestJid(cat2Mac, home2, server, resourcePath2)
    private val mobile = TestJid(mobileMac, home, server2, resourcePath3)
    private val validJidInfo = listOf(
            cat1, cat2, mobile
    )

    @Test
    fun identity_parse() {
        validJidInfo.forEach {
            val jidString = "${it.mac}_${it.home}"
            val identity = Jid.Identity.parse(jidString)
            assertThat(identity.deviceId, `is`(it.mac))
            assertThat(identity.homeId, `is`(it.home))
        }

        invalidIds.forEach { invalidString ->
            assertThrows(IllegalArgumentException::class.java) {
                Jid.Identity.parse(invalidString)
            }
        }
    }

    @Test
    fun identity_parseOrNull() {
        validJidInfo.forEach {
            val jidString = "${it.mac}_${it.home}"
            val identity = Jid.Identity.parseOrNull(jidString)
            assertThat(identity?.deviceId, `is`(it.mac))
            assertThat(identity?.homeId, `is`(it.home))
        }

        invalidIds.forEach { invalidString ->
            val nullReturn = Jid.Identity.parseOrNull(invalidString)
            assertThat(nullReturn, `is`(nullValue()))
        }
    }

    @Test
    fun identity_toString() {
        validJidInfo.forEach {
            val identityString = "${it.mac}_${it.home}"
            val identity = Jid.Identity(it.mac, it.home)
            assertThat(identity.toString(), `is`(identityString))
        }
    }

    @Test
    fun resource_parse() {
        validJidInfo.forEach {
            val resourceString = "${it.server}/${it.res}"
            val resource = Jid.Resource.parse(resourceString)
            assertThat(resource.server, `is`(it.server))
            assertThat(resource.resourcePath, `is`(it.res))
        }

        val resourceWithNoPath = Jid.Resource.parse(server)
        assertThat(resourceWithNoPath.server, `is`(server))
        assertThat(resourceWithNoPath.resourcePath, `is`(""))

        val resourceWithEmptyPath = Jid.Resource.parse("$server/")
        assertThat(resourceWithEmptyPath.server, `is`(server))
        assertThat(resourceWithEmptyPath.resourcePath, `is`(""))
    }

    @Test
    fun resource_toString() {
        validJidInfo.forEach {
            val resourceString = "${it.server}/${it.res}"
            val resource = Jid.Resource(it.server, it.res)
            assertThat(resource.toString(), `is`(resourceString))
        }

        val emptyPathString = "$server/"
        val resourceWithEmptyPath = Jid.Resource(server, "")
        assertThat(resourceWithEmptyPath.toString(), `is`(emptyPathString))
    }

    @Test
    fun parse() {
        validJidInfo.forEach {
            val jidString = "${it.mac}_${it.home}@${it.server}/${it.res}"
            val jid = Jid.parse(jidString)
            assertThat(jid.identity.deviceId, `is`(it.mac))
            assertThat(jid.identity.homeId, `is`(it.home))
            assertThat(jid.resource?.server, `is`(it.server))
            assertThat(jid.resource?.resourcePath, `is`(it.res))
        }

        invalidJidStrings.forEach { invalidString ->
            assertThrows(IllegalArgumentException::class.java) {
                Jid.parse(invalidString)
            }
        }
    }

    @Test
    fun parseOrNull() {
        validJidInfo.forEach {
            val jidString = "${it.mac}_${it.home}@${it.server}/${it.res}"
            val jid = Jid.parseOrNull(jidString)
            assertThat(jid?.identity?.deviceId, `is`(it.mac))
            assertThat(jid?.identity?.homeId, `is`(it.home))
            assertThat(jid?.resource?.server, `is`(it.server))
            assertThat(jid?.resource?.resourcePath, `is`(it.res))
        }

        listOf(
                "stringWithNoUnderscore@server/resource",
                "someOtherStringWithNoUnderscore@server/",
                "anotherOne"
        ).forEach { invalidString ->
            val nullReturn = Jid.parseOrNull(invalidString)
            assertThat(nullReturn, `is`(nullValue()))
        }
    }

    @Test
    fun jid_toString() {
        validJidInfo.forEach {
            val jidString = "${it.mac}_${it.home}@${it.server}/${it.res}"
            val jid = Jid(Jid.Identity(it.mac, it.home), Jid.Resource(it.server, it.res))
            assertThat(jid.toString(), `is`(jidString))
        }

        val jidStringWithNoResource = "${cat1Mac}_$home"
        val jidWithNoResource = Jid(Jid.Identity(cat1Mac, home))
        assertThat(jidWithNoResource.toString(), `is`(jidStringWithNoResource))
    }
}
