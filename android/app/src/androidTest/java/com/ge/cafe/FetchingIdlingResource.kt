package com.ge.cafe

import androidx.test.espresso.IdlingResource
import com.ge.cafe.a_UnderConstruction.common.test.FetcherListener

class FetchingIdlingResource: IdlingResource, FetcherListener {
    private var idle = true
    private lateinit var resourceCallback:
            IdlingResource.ResourceCallback

    override fun getName(): String {
        return FetchingIdlingResource::class.java.simpleName
    }

    override fun isIdleNow() = idle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }

    override fun doneFetching() {
        idle = true
        resourceCallback.onTransitionToIdle()
    }

    override fun beginFetching() {
        idle = false
    }
}