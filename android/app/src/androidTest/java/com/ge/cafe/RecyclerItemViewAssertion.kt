package com.ge.cafe

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.util.HumanReadables


/**
 * @file RecyclerItemViewAssertion.kt
 * @date 07/05/2020
 * @brief
 * @copyright GE Appliances, a Haier Company (Confidential). All rights reserved.
 */
class RecyclerItemViewAssertion<A>(private val position: Int, private val item: A, private val itemViewAssertion: RecyclerViewInteraction.ItemViewAssertion<A>) : ViewAssertion {
    override fun check(view: View, e: NoMatchingViewException?) {
        val recyclerView = view as RecyclerView
        val viewHolderForPosition = recyclerView.findViewHolderForLayoutPosition(position)
        if (viewHolderForPosition == null) {
            throw PerformException.Builder()
                    .withActionDescription(toString())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(IllegalStateException("No view holder at position: $position"))
                    .build()
        } else {
            val viewAtPosition = viewHolderForPosition.itemView
            itemViewAssertion.check(item, viewAtPosition, e)
        }
    }
}