package com.elevenetc.buildingbuilder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BuildingBuilderView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private var drawer: BuildingDrawer = BuildingDrawer()
    private var building: Building? = null

    fun setBuilding(building: Building) {
        this.building = building
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {

        if (building != null) drawer.draw(building!!, canvas)
    }

    class BuildingDrawer {

        private val cellPaint = Paint().apply {
            style = Paint.Style.STROKE
            color = Color.BLUE
            strokeWidth = 10f
        }

        fun draw(building: Building, canvas: Canvas) {
            drawSegments(canvas, building)
            drawCells(canvas, building)
        }

        private fun drawCells(canvas: Canvas, building: Building) {

        }

        private fun drawSegments(canvas: Canvas, building: Building) {
            canvas.save()

            for ((r, row) in building.matrix.cells.withIndex()) {

                canvas.translate(0f, row[0].height)
                canvas.save()

                for ((c, segment) in row.withIndex()) {
                    val cell = building.matrix.cells[r][c]

                    canvas.translate(cell.width, 0f)

                    segment.segment?.draw(cell.width, row[0].height, canvas)
                    drawCell(cell, canvas)
                }
                canvas.restore()
            }

            canvas.restore()
        }

        private fun drawCell(cell: BuildingMatrix.Cell, canvas: Canvas) {
            canvas.drawRect(0f, 0f, cell.width, cell.height, cellPaint)
        }
    }

}