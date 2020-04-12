package ru.yushka.firstotushomework.ui.main

import android.graphics.Canvas
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import ru.yushka.firstotushomework.R
import kotlin.math.roundToInt


class DefaultItemDecoration(
    private val isShowStart: Boolean = false,
    private val isShowMiddle: Boolean = false,
    private val isShowEnd: Boolean = false,
    private val hasHeader: Boolean = false,
    private val leftMarginStart: Int? = null,
    private val leftMarginMiddle: Int? = null,
    private val leftMarginEnd: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val divider = parent.context.getDrawableCompat(R.color.separatorBackground)!!
        val dividerSize = parent.context.dpToPixSize(1.0f)
        val bounds = Rect()

        val childCount = parent.childCount
        if (childCount == 0) return

        val right = parent.width - parent.paddingRight

        if (isShowStart) {
            val child = parent.getChildAt(0)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            val left = leftMarginStart ?: parent.context.dpToPixSize(16.0f)
            val top = bounds.top + child.translationY.roundToInt()
            val bottom = top + dividerSize
            divider.alpha = (255 * child.alpha).toInt()
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }

        if (hasHeader) {
            val child = parent.getChildAt(0)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            val left = 0
            val top = bounds.top + child.translationY.roundToInt()
            val bottom = top + dividerSize
            divider.alpha = (255 * child.alpha).toInt()
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }

        if (isShowMiddle) {
            for (i in 1 until childCount) {
                val child = parent.getChildAt(i)
                parent.getDecoratedBoundsWithMargins(child, bounds)
                val left = leftMarginMiddle ?: parent.context.dpToPixSize(16.0f)
                val top = bounds.top + child.translationY.roundToInt()
                val bottom = top + dividerSize
                divider.alpha = (255 * child.alpha).toInt()
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }

        if (isShowEnd) {
            val child = parent.getChildAt(childCount - 1)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            val left = leftMarginEnd ?: parent.context.dpToPixSize(16.0f)
            val bottom = bounds.bottom + child.translationY.roundToInt()
            val top = bottom - dividerSize
            divider.alpha = (255 * child.alpha).toInt()
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}
