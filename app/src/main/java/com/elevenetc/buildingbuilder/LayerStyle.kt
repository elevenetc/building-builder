package com.elevenetc.buildingbuilder

import android.graphics.Canvas

class LayerStyle(val layerValues: LayerValues, val invalidator: () -> Unit) {


    private val background = LayerBackground(layerValues, invalidator)
    private val foreground = LayerForeground(layerValues, invalidator)

    init {

    }

    fun initDraw(model: LayerModel, canvas: Canvas) {
        background.initDraw(canvas)
        foreground.initDraw(canvas)
    }

    fun drawForeground(model: LayerModel, canvas: Canvas) {
        foreground.draw(canvas)
    }

    fun drawBackground(model: LayerModel, canvas: Canvas) {
        background.draw(canvas)
    }

    fun onSizeChanged(values: LayerValues) {
        background.onSizeChanged()
        foreground.onSizeChanged()
    }

}