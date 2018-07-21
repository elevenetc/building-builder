package com.elevenetc.buildingbuilder

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LayerStyle(val grid: Grid, val invalidator: () -> Unit) {

    val windows = mutableListOf<MutableList<Window>>()

    init {
        for (r in 0..grid.height) {

            val row = mutableListOf<Window>()
            windows.add(row)

            for (c in 0..grid.width) {
                row.add(Window(invalidator))
            }
        }
    }

    private val fillPaint = Paint().apply {
        color = Color.parseColor("#0055aa")
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    fun drawBackground(model: LayerModel, canvas: Canvas) {
        canvas.drawRect(0f, 0f, model.width.toFloat(), model.height.toFloat(), fillPaint)
        canvas.drawRect(0f, 0f, model.width.toFloat(), model.height.toFloat(), strokePaint)
    }

    fun onModelWidthSizeChanged(before: Int, now: Int) {

    }

    fun onModelHeightSizeChanged(before: Int, now: Int) {

    }

    fun drawForeground(model: LayerModel, canvas: Canvas) {
        for (r in 0..grid.height) {
            val row = windows[r]
            for (c in 0..grid.width)
                row[c].draw(r, c, model.grid.cellWidth, model.grid.cellHeight, canvas)

        }
    }

    class Window(private val invalidator: () -> Unit) {

        private val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = Color.RED
            alpha = 0
        }

        fun draw(cellR: Int, cellC: Int,
                 cellWidth: Int, cellHeight: Int,
                 canvas: Canvas) {

            val padding = 20
            val top = cellR * cellHeight + padding
            val left = cellC * cellWidth + padding
            val right = left + cellWidth - padding * 2
            val bottom = top + cellHeight - padding * 2

            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }

        fun show() {
            val animator = ValueAnimator.ofInt(0, 255)
            animator.addUpdateListener {
                paint.alpha = it.animatedValue as Int
                invalidator()
            }
            animator.start()
        }

        fun hide() {
            val animator = ValueAnimator.ofInt(255, 0)
            animator.addUpdateListener {
                paint.alpha = it.animatedValue as Int
                invalidator()
            }
            animator.start()
        }

    }
}