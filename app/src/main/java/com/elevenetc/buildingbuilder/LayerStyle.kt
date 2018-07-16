package com.elevenetc.buildingbuilder

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LayerStyle {

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
}