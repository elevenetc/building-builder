package com.elevenetc.buildingbuilder

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LayerBackground(
        private val layerValues: LayerValues,
        private val invalidate: () -> Unit) {

    private var width: Int = layerValues.width
    private var height: Int = layerValues.height
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
        setAnimator(widthAnimator, width, layerValues.width * layerValues.cellWidth) { width = it }
        setAnimator(heightAnimator, height, layerValues.height * layerValues.cellHeight) { height = it }
    }

    fun initDraw(canvas: Canvas) {
        width = layerValues.width * layerValues.cellWidth
        height = layerValues.height * layerValues.cellHeight
        draw(canvas)
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), fillPaint)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), strokePaint)
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