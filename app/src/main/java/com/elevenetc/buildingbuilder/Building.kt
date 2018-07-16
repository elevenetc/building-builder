package com.elevenetc.buildingbuilder

class Building {

    val matrix = BuildingMatrix()
    var currentWidth = 0
    var currentHeight = 0

    init {

    }

    fun setWidth(w: Int) {

    }

    fun setHeight(h: Int) {

        for ((r, row) in matrix.cells.withIndex()) {
            for ((c, cell) in row.withIndex()) {

            }
        }
    }

    fun setSegments(segments: MutableList<MutableList<Segment>>) {

        currentWidth = segments[0].size
        currentHeight = segments.size

        for ((index, segmentsRow) in segments.withIndex()) {

            val rowCells = matrix.cells[index]

            for ((index, segment) in segmentsRow.withIndex()) {
                rowCells[index].segment = segment
            }
        }
    }
}