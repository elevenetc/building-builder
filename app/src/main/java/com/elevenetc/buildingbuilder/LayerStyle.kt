package com.elevenetc.buildingbuilder

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LayerStyle(val layerValues: LayerValues, val invalidator: () -> Unit) {

    val windows = mutableListOf<MutableList<Window>>()

    init {
        for (r in 0 until layerValues.maxHeight) {

            val row = mutableListOf<Window>()
            windows.add(row)

            for (c in 0 until layerValues.maxWidth) {
                row.add(Window(invalidator))
            }
        }
    }

    private val fillPaint = Paint().apply {
        color = Color.parseColor("#0055aa")
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    fun drawBackground(model: LayerModel, canvas: Canvas) {

        val width = model.values.width * model.values.cellWidth.toFloat()
        val height = model.values.height * model.values.cellHeight.toFloat()

        canvas.drawRect(0f, 0f, width, height, fillPaint)
        canvas.drawRect(0f, 0f, width, height, strokePaint)
    }

    fun onSizeChanged(values: LayerValues) {


        for (r in 0 until windows.size)
            for (c in 0 until windows[r].size) {
                val w = windows[r][c]

                if (r <= values.height && c <= values.width) {
                    w.show()
                } else {
                    w.hide()
                }
            }
    }

    fun drawForeground(model: LayerModel, canvas: Canvas) {
        for (r in 0 until layerValues.height) {
            val row = windows[r]
            for (c in 0 until layerValues.width)
                row[c].draw(r, c, model.values.cellWidth, model.values.cellHeight, canvas)

        }
    }

    class Window(private val invalidate: () -> Unit) {

        private val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = Color.RED
        }

        private var visible = false

        fun draw(cellR: Int, cellC: Int,
                 cellWidth: Int, cellHeight: Int,
                 canvas: Canvas) {

            val padding = 20
            val top = cellR * cellHeight + padding
            val left = cellC * cellWidth + padding
            val right = left + cellWidth - padding * 2
            val bottom = top + cellHeight - padding * 2

            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }

        var animator: ValueAnimator = ValueAnimator.ofInt(0)

        fun show() {

            if (visible) return
            visible = true

            initAnim(paint.alpha, 255) {
                if (!visible) hide()
            }
        }

        fun hide() {
            if (!visible) return
            visible = false

            initAnim(paint.alpha, 0) {
                if (visible) show()
            }
        }

        fun initAnim(from: Int, to: Int, onEnd: () -> Unit) {

            if (animator.isRunning) animator.cancel()

            animator.cancel()

            animator = ValueAnimator.ofInt(from, to)
            animator.duration = 500

            animator.addUpdateListener {
                paint.alpha = it.animatedValue as Int
                invalidate()
            }

            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationCancel(p0: Animator?) {

                }

                override fun onAnimationStart(p0: Animator?) {

                }

                override fun onAnimationRepeat(p0: Animator?) {

                }

                override fun onAnimationEnd(p0: Animator?) {
                    onEnd()
                }

            })
            animator.start()
        }

    }
}