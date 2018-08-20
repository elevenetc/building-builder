package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class Layer(
        val style: LayerStyle,
        val model: LayerModel
) {

    var initDraw = false

    init {

    }

    fun draw(canvas: Canvas) {

        if (!initDraw) {
            initDraw = true
            style.initDraw(model, canvas)
        } else {
            style.drawBackground(model, canvas)
            style.drawForeground(model, canvas)
        }

    }

}