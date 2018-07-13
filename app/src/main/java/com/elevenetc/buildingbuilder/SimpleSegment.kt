package com.elevenetc.buildingbuilder

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class SimpleSegment(val color: Int) : Segment {

    val fillPaint = Paint()
    val storkePaint = Paint()

    init {
        fillPaint.style = Paint.Style.FILL
        fillPaint.color = color

        storkePaint.style = Paint.Style.STROKE
        storkePaint.color = Color.WHITE
        storkePaint.strokeWidth = 10f
    }

    override fun draw(width: Float, height: Float, canvas: Canvas) {
        canvas.drawRect(0f, 0f, width, height, fillPaint)
        //canvas.drawRect(0f, 0f, width, height, storkePaint)
    }
}