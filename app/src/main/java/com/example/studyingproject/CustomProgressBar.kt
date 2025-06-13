package com.example.studyingproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CustomProgressBar(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        strokeWidth = 30f
        strokeCap = Paint.Cap.ROUND
    }
    private var progress = 0f
    private var customSize = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val defaultSize =
            min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
        val size = if (customSize > 0)
            customSize
        else
            defaultSize
        setMeasuredDimension(size.toInt(), size.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val padding = paint.strokeWidth / 2
        val size = width.toFloat()
        paint.color = when {
            progress < 30f -> Color.RED
            progress < 70f -> Color.parseColor("#FFA500") // оранжевый
            else -> Color.GREEN
        }

        val sweepAngle = (progress / 100f) * 360f
        canvas.drawArc(
            padding,
            padding,
            size - padding,
            size - padding,
            -90f,
            sweepAngle,
            true,
            paint
        )
    }

    fun setProgress(value: Float) {
        progress = value.coerceIn(0f, 100f)
        invalidate()
    }

    fun setCustomSize(sizeInPx: Float) {
        customSize = sizeInPx
        requestLayout()
    }

}