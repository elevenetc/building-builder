package com.elevenetc.buildingbuilder

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

        val initWidth = 300
        val initHeight = 300
        val width = 5
        val height = 10
        val cellWidth = 100
        val cellHeight = 100

        seekBarWidth.min = cellWidth * 1
        seekBarHeight.min = cellHeight * 1
        seekBarWidth.max = cellWidth * width
        seekBarHeight.max = cellHeight * height

        seekBarWidth.progress = initWidth
        seekBarHeight.progress = initHeight

        val grid = Grid(600, 600, 100, 100, width, height)
        val model = LayerModel(initWidth, initHeight, grid)
        val style = LayerStyle(grid) { builderView.invalidate() }
        val layer = Layer(
                style,
                model
        )

        builderView.addLayer(layer)

        seekBarWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                model.updateWidth(progress)
                builderView.invalidate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                builderView.fitWidthGrid { progress ->
                    seekBarWidth.progress = progress
                }
            }

        })


        seekBarHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                model.updateHeight(progress)
                builderView.invalidate()
            }

            override fun onStartTrackingTouch(progress: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                builderView.fitHeightGrid { progress ->
                    seekBarHeight.progress = progress
                }
            }

        })
    }


}
