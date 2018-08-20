package com.elevenetc.buildingbuilder

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LayerBackground {

    var width: Float = 0f
    var height: Float = 0f

    private val fillPaint = Paint().apply {
        color = Color.parseColor("#0055aa")
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    fun initDraw(model: LayerModel, canvas: Canvas) {
        width = model.values.width * model.values.cellWidth.toFloat()
        height = model.values.height * model.values.cellHeight.toFloat()
        draw(canvas)
    }

    fun draw(model: LayerModel, canvas: Canvas) {
        width = model.values.width * model.values.cellWidth.toFloat()
        height = model.values.height * model.values.cellHeight.toFloat()
        draw(canvas)
    }

    private fun draw(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width, height, fillPaint)
        canvas.drawRect(0f, 0f, width, height, strokePaint)
    }
}