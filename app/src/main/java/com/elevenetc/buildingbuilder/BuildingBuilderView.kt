package com.elevenetc.buildingbuilder

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class BuildingBuilderView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    lateinit var layer: Layer
    private val gridPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.5f
        color = Color.GRAY
        alpha = 100
    }

    fun addLayer(layer: Layer) {
        this.layer = layer
        invalidate()
    }

    fun fitWidthGrid(handler: (Int) -> Unit) {
        if (!layer.fitsGrid()) {

            val wAnim = ValueAnimator.ofInt(layer.model.width, layer.model.cellsWidth)

            wAnim.addUpdateListener { updatedAnimation ->
                val value = updatedAnimation.animatedValue as Int
                handler(value)
                //layer.model.width = value
                //invalidate()
            }

            wAnim.start()
        }
    }

    fun fitHeightGrid(handler: (Int) -> Unit) {
        if (!layer.fitsGrid()) {

            val hAnim = ValueAnimator.ofInt(layer.model.height, layer.model.cellsHeight)

            hAnim.addUpdateListener { updatedAnimation ->
                val value = updatedAnimation.animatedValue as Int
                handler(value)
                //layer.model.height = updatedAnimation.animatedValue as Int
                //invalidate()
            }

            hAnim.start()
        }
    }

    override fun onDraw(canvas: Canvas) {

        layer.draw(canvas)

        val grid = layer.model.grid
        val max = grid.maxWidth

        for (h in 0..max step grid.cellHeight) {
            canvas.drawLine(0f, h.toFloat(), max.toFloat(), h.toFloat(), gridPaint)
        }

        for (w in 0..max step grid.cellWidth) {
            canvas.drawLine(w.toFloat(), 0f, w.toFloat(), max.toFloat(), gridPaint)
        }

    }
}