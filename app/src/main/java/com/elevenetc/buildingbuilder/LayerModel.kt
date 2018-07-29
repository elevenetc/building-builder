package com.elevenetc.buildingbuilder

class LayerModel(
        val values: LayerValues
) {

    var sizeChangeHandler: ((values: LayerValues) -> Unit)? = null

    init {

    }

    fun updateWidth(width: Int) {
        if (values.width == width) return
        values.width = width
        sizeChangeHandler?.invoke(values)
    }

    fun updateHeight(height: Int) {
        if (values.height == height) return
        values.height = height
        sizeChangeHandler?.invoke(values)
    }

    /**
     * Returns size in pixels
     */
    private fun fit(size: Int, step: Int): Int {
        val rem = size % step
        return if (rem < step / 2) size - rem
        else size - rem + step
    }
}