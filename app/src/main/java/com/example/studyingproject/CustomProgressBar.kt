package com.example.studyingproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ProgressBarView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 10f
        isAntiAlias = true
    }

    private val progressPaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
        isAntiAlias = true
    }


    private var progress = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val left = 100f
        val top = 100f
        val right = 600f
        val bottom = 200f
        val radius = 30f
        canvas.drawRoundRect(left, top, right, bottom, radius, radius, paint)
        val progressWidth = (right - left) * (progress / 100)

        if (progress > 0) {
            canvas.drawRoundRect(
                left,
                top,
                left + progressWidth,
                bottom,
                radius,
                radius,
                progressPaint
            )
            canvas.drawText("Loading... ${progress.toInt()}", 225f, 160f, textPaint)
        }
    }

    fun changeProgress(newProgress: Float) {
        progress = newProgress.coerceIn(0f, 100f)
        invalidate()
    }
}