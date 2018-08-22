package com.elevenetc.buildingbuilder

class LayerValues(
        var width: Int,//windows count
        var height: Int,//windows count
        val maxWidth: Int,//max windows count
        val maxHeight: Int,//max windows count
        var cellWidth: Int,//px
        var cellHeight: Int,//px
        var bottom: Int,//px
        var centerX: Int//px


) {

    fun maxPxWidth(): Int {
        return maxWidth * cellWidth
    }

    fun maxPxHeight(): Int {
        return maxHeight * cellHeight
    }

    fun currentPxWidth(): Int {
        return width * cellWidth
    }

    fun pxHeight(): Int {
        return height * cellHeight
    }
}