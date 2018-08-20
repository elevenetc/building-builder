package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class LayerForeground(val layerValues: LayerValues, val invalidator: () -> Unit) {

    private val windows = mutableListOf<MutableList<Window>>()

    init {
        for (r in 0 until layerValues.maxHeight) {

            val row = mutableListOf<Window>()
            windows.add(row)

            for (c in 0 until layerValues.maxWidth) {
                row.add(Window(
                        Window.Model(r, c, layerValues.cellWidth, layerValues.cellHeight),
                        invalidator)
                )
            }
        }
    }

    fun initDraw(canvas: Canvas) {
        traverseWindows(false)
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
        traverseWindows(true)
    }

    private fun traverseWindows(animate: Boolean) {
        for (r in 0 until windows.size)
            for (c in 0 until windows[r].size) {
                val w = windows[r][c]

                if (r <= layerValues.height - 1 && c <= layerValues.width - 1) {
                    w.show(animate)
                } else {
                    w.hide(animate)
                }
            }
    }
}