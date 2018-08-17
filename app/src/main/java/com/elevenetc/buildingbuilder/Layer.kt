package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class Layer(
        val style: LayerStyle,
        val model: LayerModel
) {

    var initDraw = false

    init {

//        model.sizeChangeHandler = { w, h ->
//            style.onSizeChanged(w, h)
//        }

    }

    fun draw(canvas: Canvas) {

        //style.drawBackground(model, canvas)
        style.drawForeground(model, canvas)

//        if (!initDraw) {
//            initDraw = true
//            style.drawBackground(model, canvas)
//            style.drawForeground(model, canvas)
//        } else {
//            style.drawBackground(model, canvas)
//        }

    }

//    fun fitsGrid(): Boolean {
//        val grid = model.values
//        return model.width % grid.cellWidth == 0 && model.height % grid.cellHeight == 0
//    }

}