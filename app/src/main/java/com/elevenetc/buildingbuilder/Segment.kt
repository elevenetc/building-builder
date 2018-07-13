package com.elevenetc.buildingbuilder

import android.graphics.Canvas

interface Segment {
    fun draw(width: Float, height: Float, canvas: Canvas)
    //fun location(): SegmentLocation
    //fun hideAnimated()
    //fun showAnimated()
}