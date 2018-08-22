package com.elevenetc.buildingbuilder

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Window(
        private val model: Model,
        private val values: LayerValues,
        private val invalidate: () -> Unit
) {

    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.RED
        alpha = 0
    }

    private var visible = false
    private var animator: ValueAnimator = ValueAnimator.ofInt(0)
    private val padding = 20

    fun draw(canvas: Canvas) {

        val leftShift = values.centerX - values.maxPxWidth() / 2
        val topShift = values.bottom - values.maxPxHeight()

        val top = topShift + model.cellR * model.cellHeight + padding
        val left = leftShift + model.cellC * model.cellWidth + padding
        val right = left + model.cellWidth - padding * 2
        val bottom = top + model.cellHeight - padding * 2

        canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
    }

    fun show(animate: Boolean = true) {

        if (animate) {

            if (visible) return
            visible = true

            initAnim(paint.alpha, 255) {
                if (!visible) hide()
            }
        } else {
            visible = true
            paint.alpha = 255
        }
    }

    fun hide(animate: Boolean = true) {


        if (animate) {

            if (!visible) return
            visible = false

            initAnim(paint.alpha, 0) {
                if (visible) show()
            }
        } else {
            visible = false
            paint.alpha = 0
        }
    }

    private fun initAnim(from: Int, to: Int, onEnd: () -> Unit) {

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

    data class Model(val cellR: Int, val cellC: Int, val cellWidth: Int, val cellHeight: Int)

}