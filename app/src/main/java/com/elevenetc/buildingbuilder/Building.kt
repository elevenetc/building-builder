package com.elevenetc.buildingbuilder

class Building(
        val segmentsMatrix: MutableList<MutableList<Segment>>
) {

    val rows = mutableListOf<Row>()
    val columns = mutableListOf<Column>()

    init {
        for ((r, rowSegments) in segmentsMatrix.withIndex()) {

            val row = Row()
            row.height = 100f
            rows.add(row)

            for ((c, segment) in rowSegments.withIndex()) {

                val column: Column
                if (columns.size <= c) {
                    column = Column()
                    column.width = 100f
                    columns.add(column)
                } else {
                    column = columns[c]
                }

                column.segments.add(segment)
                row.segments.add(segment)
            }
        }
    }
}