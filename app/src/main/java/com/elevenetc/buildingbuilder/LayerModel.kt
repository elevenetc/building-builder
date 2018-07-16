package com.elevenetc.buildingbuilder

class LayerModel(
        var width: Int,
        var height: Int,
        val grid: Grid
) {

    var cellsWidth: Int = 0
    var cellsHeight: Int = 0

    init {
        calcFitSize()
    }

    fun updateWidth(width: Int) {
        if (this.width == width) return
        this.width = width
        calcFitSize()
    }

    fun updateHeight(height: Int) {
        if (this.height == height) return
        this.height = height
        calcFitSize()
    }

    private fun calcFitSize() {
        cellsWidth = fit(width, grid.cellWidth)
        cellsHeight = fit(height, grid.cellHeight)
    }

    private fun fit(size: Int, step: Int): Int {
        val rem = size % step
        return if (rem < step / 2) size - rem
        else size - rem + step
    }
}