package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class Layer(
        val style: LayerStyle,
        val model: LayerModel
) {

    var initDraw = false

    init {
        model.heightHandler = { before, now ->
            style.onModelHeightSizeChanged(before, now)
        }

        model.widthHandler = { before, now ->
            style.onModelWidthSizeChanged(before, now)
        }


    }

    fun draw(canvas: Canvas) {
        if (!initDraw) {
            initDraw = true
            style.drawBackground(model, canvas)
            style.drawForeground(model, canvas)
        } else {
            style.drawBackground(model, canvas)
        }

    }

    fun fitsGrid(): Boolean {
        val grid = model.grid
        return model.width % grid.cellWidth == 0 && model.height % grid.cellHeight == 0
    }

}