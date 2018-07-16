package com.elevenetc.buildingbuilder

class BuildingMatrix {

    val cells = mutableListOf<MutableList<Cell>>()

    init {

        val rows = 100
        val columns = 100

        for (r in 0 until rows) {

            val row = mutableListOf<Cell>()
            cells.add(row)

            for (c in 0 until columns) {
                row.add(Cell(100f, 100f))
            }
        }
    }

    class Cell(
            var width: Float = 0f,
            var height: Float = 0f,
            var segment: Segment? = null
    )
}