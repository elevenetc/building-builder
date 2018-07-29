package com.elevenetc.buildingbuilder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builderView = findViewById<BuildingBuilderView>(R.id.builder_view)
        val btnAddWidth = findViewById<Button>(R.id.btn_add_width)
        val btnReduceWidth = findViewById<Button>(R.id.btn_reduce_width)
        val textStatusWidth = findViewById<TextView>(R.id.text_width)

        val btnAddHeight = findViewById<Button>(R.id.btn_add_height)
        val btnReduceHeight = findViewById<Button>(R.id.btn_reduce_height)
        val textStatusHeight = findViewById<TextView>(R.id.text_height)

        val maxWidth = 10
        val minWidth = 1
        val maxHeight = 5
        val minHeight = 1

        val cellWidth = 100
        val cellHeight = 100

        var currentWidth = 3
        var currentHeight = 2

        val values = LayerValues(currentWidth, currentHeight, maxWidth, maxHeight, cellWidth, cellHeight)
        val model = LayerModel(values)
        val style = LayerStyle(values) { builderView.invalidate() }
        val layer = Layer(style, model)

        AddReduceView(btnAddWidth, btnReduceWidth, textStatusWidth, maxWidth, minWidth, currentWidth) { w ->
            model.updateWidth(w)
        }

        AddReduceView(btnAddHeight, btnReduceHeight, textStatusHeight, maxHeight, minHeight, currentHeight) { h ->
            model.updateHeight(h)
        }

        model.sizeChangeHandler = { values ->
            style.onSizeChanged(values)
        }

        builderView.addLayer(layer)
    }

    internal class AddReduceView(
            val btnAdd: Button,
            val btnReduce: Button,
            val textStatus: TextView,
            val max: Int,
            val min: Int,
            var current: Int,
            val changeHandler: (current: Int) -> Unit
    ) {
        init {
            btnAdd.setOnClickListener {
                if (current < max) {
                    current++
                    changeHandler(current)
                    updateViewState()
                }
            }

            btnReduce.setOnClickListener {
                if (current > min) {
                    current--
                    changeHandler(current)
                    updateViewState()
                }
            }

            updateViewState()
        }

        private fun updateViewState() {
            textStatus.text = current.toString()
            btnReduce.isEnabled = current != min
            btnAdd.isEnabled = current != max
        }
    }


}
