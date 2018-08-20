package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class LayerStyle(val layerValues: LayerValues, val invalidator: () -> Unit) {


    private val background = LayerBackground()
    private val foreground = LayerForeground(layerValues, invalidator)

    init {

    }

    fun initDraw(model: LayerModel, canvas: Canvas) {
        background.initDraw(model, canvas)
        foreground.initDraw(canvas)
    }

    fun drawForeground(model: LayerModel, canvas: Canvas) {
        foreground.draw(canvas)
    }

    fun drawBackground(model: LayerModel, canvas: Canvas) {
        background.draw(model, canvas)
    }

    fun onSizeChanged(values: LayerValues) {
        foreground.onSizeChanged()
    }

}