/*
 * Copyright (c) 2021. GE Appliances, a Haier Company (Confidential)
 *
 * All Rights Reserved.
 */

package com.ge.commonframework.https.jsonstructure.oven

import com.google.gson.Gson
import org.junit.Test
import java.io.ObjectOutputStream

class CookSettingTest {

    private val cookSetting = CookSetting()

    @Test
    fun serializeGson() {
        val gson = Gson()

        // assert no crash
        gson.toJson(cookSetting)
    }

    @Test
    fun serializeJava() {
        val outputStream = ObjectOutputStream(System.out)

        // assert no crash
        outputStream.writeObject(cookSetting)
    }
}
