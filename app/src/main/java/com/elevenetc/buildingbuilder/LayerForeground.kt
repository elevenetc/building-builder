package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class LayerForeground(
        val layerValues: LayerValues,
        val invalidator: () -> Unit
) {

    private val windows = mutableListOf<MutableList<Window>>()

    init {
        for (r in 0 until layerValues.maxHeight) {

            val row = mutableListOf<Window>()
            windows.add(row)

            for (c in 0 until layerValues.maxWidth) {
                row.add(Window(
                        Window.Model(r, c, layerValues.cellWidth, layerValues.cellHeight),
                        layerValues,
                        invalidator)
                )
            }
        }
    }

    fun initDraw(canvas: Canvas) {
        showOrHideWindows(false)
        invalidator.invoke()
    }

    fun draw(canvas: Canvas) {
        for (r in 0 until windows.size) {
            val row = windows[r]
            for (c in 0 until windows[r].size)
                row[c].draw(canvas)
        }
    }

    fun onSizeChanged() {
        showOrHideWindows(true)
    }

    private fun showOrHideWindows(animate: Boolean) {
        for (r in 0 until windows.size) {

            val row = windows[r]
            var left = 0
            var right = row.size - 1
            val halfWidth = layerValues.width / 2
            val w = row.size / 2 - halfWidth
            val h = windows.size - layerValues.height

            if (r < h) {
                row.forEach { window -> window.hide(animate) }
                continue //hide top invisible rows completely
            }

            while (left <= right) {

                val colIsInvisible = left < w

                if (colIsInvisible) {
                    row[left].hide(animate)
                    row[right].hide(animate)
                } else {
                    row[left].show(animate)
                    row[right].show(animate)
                }

                left++
                right--
            }
        }
    }
}