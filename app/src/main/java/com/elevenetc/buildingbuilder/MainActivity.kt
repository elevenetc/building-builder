package com.elevenetc.buildingbuilder

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builderView = findViewById<BuildingBuilderView>(R.id.builder_view)
        val seekBarWidth = findViewById<SeekBar>(R.id.seek_bar_width)
        val seekBarHeight = findViewById<SeekBar>(R.id.seek_bar_height)

        seekBarWidth.min = 1
        seekBarHeight.min = 1
        seekBarWidth.max = 20
        seekBarHeight.max = 10

        seekBarWidth.progress = 5
        seekBarHeight.progress = 3

        val configurator = BuildingConfigurator(object : SegmentFactory {
            override fun make(): Segment {
                return SimpleSegment(Color.RED)
            }

        })

        configurator.setColumnsAmount(seekBarWidth.progress)
        configurator.setRowsAmount(seekBarHeight.progress)

        builderView.setBuilding(configurator.build())



        seekBarWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                configurator.setColumnsAmount(progress)
                builderView.setBuilding(configurator.build())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })



        seekBarHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                configurator.setRowsAmount(progress)
                builderView.setBuilding(configurator.build())
            }

            override fun onStartTrackingTouch(progress: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    class BuildingConfigurator(private val segmentFactory: SegmentFactory) {

        private var columns: Int = 0
        private var rows: Int = 0

        fun setColumnsAmount(c: Int) {
            columns = c - 1
        }

        fun setRowsAmount(r: Int) {
            rows = r - 1
        }

        fun build(): Building {

            val buildingMatrix = mutableListOf<MutableList<Segment>>()

            for (r in 0..rows) {

                val row = mutableListOf<Segment>()
                buildingMatrix.add(row)

                for (c in 0..columns) {
                    row.add(segmentFactory.make())
                }
            }

            return Building(buildingMatrix)
        }
    }

    interface SegmentFactory {
        fun make(): Segment
    }


}
