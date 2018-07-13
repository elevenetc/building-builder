package com.elevenetc.buildingbuilder

import android.content.Context
import android.graphics.Canvas
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

        fun draw(building: Building, canvas: Canvas) {

            canvas.save()

            for ((r, row) in building.rows.withIndex()) {

                canvas.translate(0f, row.height)
                canvas.save()

                for ((c, segment) in row.segments.withIndex()) {
                    val column = building.columns[c]

                    canvas.translate(column.width, 0f)
                    segment.draw(column.width, row.height, canvas)
                }
                canvas.restore()
            }

            canvas.restore()
        }
    }

}