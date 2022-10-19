package com.ge.cafe

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition

import android.view.View;

import org.hamcrest.Matcher;


/**
 * @file RecyclerViewInteraction.kt
 * @date 07/05/2020
 * @brief
 * @copyright GE Appliances, a Haier Company (Confidential). All rights reserved.
 */

class RecyclerViewInteraction<A>(private val viewMatcher: Matcher<View>) {
    private lateinit var items: MutableList<A>
    fun withItems(items: MutableList<A>): RecyclerViewInteraction<A> {
        this.items = items
        return this
    }

    fun check(itemViewAssertion: ItemViewAssertion<A>): RecyclerViewInteraction<A> {
        for (i in items.indices) {
            onView(viewMatcher)
                    .perform(scrollToPosition<RecyclerView.ViewHolder>(i))
                    .check(RecyclerItemViewAssertion(i, items[i], itemViewAssertion))
        }
        return this
    }

    interface ItemViewAssertion<A> {
        fun check(item: A, view: View, e: NoMatchingViewException?)
    }

    companion object {
        fun <A> onRecyclerView(viewMatcher: Matcher<View>): RecyclerViewInteraction<A> {
            return RecyclerViewInteraction(viewMatcher)
        }
    }
}