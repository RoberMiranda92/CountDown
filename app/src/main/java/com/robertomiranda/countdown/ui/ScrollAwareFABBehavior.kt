package com.robertomiranda.countdown.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

//From https://www.programming-books.io/essential/android/show-and-hide-floatingactionbutton-on-scroll-5d88aa59a8344fcc8a6ff878fac19388
// FIX https://stackoverflow.com/questions/42068994/floating-action-button-fab-behavior-stops-onnestedscroll-after-hide
class ScrollAwareFABBehavior(
    context: Context?,
    attrs: AttributeSet?
) : CoordinatorLayout.Behavior<ExtendedFloatingActionButton>() {

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: ExtendedFloatingActionButton,
        directTargetChild: View, target: View, axes: Int, type: Int
    ): Boolean {
        return (axes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        ))
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: ExtendedFloatingActionButton,
        target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int,
        dyUnconsumed: Int, type: Int, consumed: IntArray
    ) {
        if (dyConsumed > 0 && child.isVisible) {
            // Fix to avoid onNestedScroll stops
            child.hide(object : ExtendedFloatingActionButton.OnChangedCallback() {

                override fun onHidden(extendedFab: ExtendedFloatingActionButton) {
                    extendedFab.visibility = View.INVISIBLE
                }
            })
        } else if (dyConsumed < 0 && !child.isVisible) {
            child.show()
        }

        super.onNestedScroll(
            coordinatorLayout, child, target, dxConsumed,
            dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed
        )
    }
}