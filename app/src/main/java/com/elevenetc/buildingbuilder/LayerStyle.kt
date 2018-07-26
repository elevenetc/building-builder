package com.elevenetc.buildingbuilder

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LayerStyle(val grid: Grid, val invalidator: () -> Unit) {

    val windows = mutableListOf<MutableList<Window>>()

    init {
        for (r in 0 until grid.height) {

            val row = mutableListOf<Window>()
            windows.add(row)

            for (c in 0 until grid.width) {
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
        canvas.drawRect(0f, 0f, model.width.toFloat(), model.height.toFloat(), fillPaint)
        canvas.drawRect(0f, 0f, model.width.toFloat(), model.height.toFloat(), strokePaint)
    }

    fun onModelWidthSizeChanged(before: Int, now: Int) {

    }

    fun onModelHeightSizeChanged(before: Int, now: Int) {

        for (r in 0 until windows.size) {
            for (w in windows[r]) {
                if (r < now) {
                    w.show()
                } else {
                    w.hide()
                }

            }
        }
    }

    fun drawForeground(model: LayerModel, canvas: Canvas) {
        for (r in 0 until grid.height) {
            val row = windows[r]
            for (c in 0 until grid.width)
                row[c].draw(r, c, model.grid.cellWidth, model.grid.cellHeight, canvas)

        }
    }

    class Window(private val invalidator: () -> Unit) {

        var alphaZ = 0

        private val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = Color.RED
            alpha = alphaZ
        }

        private var visible = false
        private var animation = false

        fun draw(cellR: Int, cellC: Int,
                 cellWidth: Int, cellHeight: Int,
                 canvas: Canvas) {

            if (!visible) return

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
            if (animation) return

            initAnim(alphaZ, 255) {
                if (!visible) hide()
            }
        }

        fun hide() {
            if (!visible) return
            visible = false
            if (animation) return

            initAnim(alphaZ, 0) {
                if (visible) show()
            }
        }

        fun initAnim(from: Int, to: Int, onEnd: () -> Unit) {
            animation = true

            animator.cancel()

            animator = ValueAnimator.ofInt(from, to)
            animator.duration = 1000

            animator.addUpdateListener {
                alphaZ = it.animatedValue as Int
                paint.alpha = alphaZ
                invalidator()
            }

            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationCancel(p0: Animator?) {
                    animation = false
                }

                override fun onAnimationStart(p0: Animator?) {

                }

                override fun onAnimationRepeat(p0: Animator?) {

                }

                override fun onAnimationEnd(p0: Animator?) {
                    animation = false
                    onEnd()
                }

            })
            animator.start()
        }

    }
}