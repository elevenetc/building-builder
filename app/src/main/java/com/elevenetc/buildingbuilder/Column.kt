package com.elevenetc.buildingbuilder

data class Column(
        val cells: MutableList<BuildingMatrix.Cell> = mutableListOf(),
        var width: Float = 0f
)