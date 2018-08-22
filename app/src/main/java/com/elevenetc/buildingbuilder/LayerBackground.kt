package com.elevenetc.buildingbuilder

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LayerBackground(
        private val values: LayerValues,
        private val invalidate: () -> Unit) {

    private var width: Int = values.width
    private var height: Int = values.height
    private var widthAnimator: ValueAnimator = ValueAnimator.ofInt(0)
    private var heightAnimator: ValueAnimator = ValueAnimator.ofInt(0)

    private val fillPaint = Paint().apply {
        color = Color.parseColor("#0055aa")
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    fun onSizeChanged() {
        setAnimator(widthAnimator, width, values.width * values.cellWidth) { width = it }
        setAnimator(heightAnimator, height, values.height * values.cellHeight) { height = it }
    }

    fun initDraw(canvas: Canvas) {
        width = values.width * values.cellWidth
        height = values.height * values.cellHeight
        draw(canvas)
    }

    fun draw(canvas: Canvas) {

        val left = values.centerX - width.toFloat() / 2
        val right = values.centerX + width.toFloat() / 2
        val top = values.bottom - height.toFloat()
        val bottom = values.bottom.toFloat()

        canvas.drawRect(left, top, right, bottom, fillPaint)
        canvas.drawRect(left, top, right, bottom, strokePaint)
    }

    private fun setAnimator(oldAnim: ValueAnimator, from: Int, to: Int, onUpdate: (value: Int) -> Unit): ValueAnimator {

        if (from == to) return oldAnim

        oldAnim.cancel()

        val animator = ValueAnimator.ofInt(from, to)
        animator.duration = 500

        animator.addUpdateListener {
            onUpdate(it.animatedValue as Int)
            invalidate()
        }

        animator.start()
        return animator
    }
}