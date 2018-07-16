package com.elevenetc.buildingbuilder

data class Row(
        val cells: MutableList<BuildingMatrix.Cell> = mutableListOf(),
        var height: Float = 0f
)