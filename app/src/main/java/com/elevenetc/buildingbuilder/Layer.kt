package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class Layer(
        val style: LayerStyle,
        val model: LayerModel
) {

    fun draw(canvas: Canvas) {
        style.drawBackground(model, canvas)
    }

    fun fitsGrid(): Boolean {
        val grid = model.grid
        return model.width % grid.cellWidth == 0 && model.height % grid.cellHeight == 0
    }

}