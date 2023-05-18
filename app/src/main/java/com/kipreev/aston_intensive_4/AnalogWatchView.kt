package com.kipreev.aston_intensive_4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class AnalogWatchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context, attrs, defStyleAttr
) {

    private var countHours = 0
    private var countMin = 0
    private var countSec = 0
    private val borderAroundClock = 20f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)
        drawCentreCircle(canvas)
        setTime()
        drawHourArrow(canvas)
        drawMinuteArrow(canvas)
        drawSecondArrow(canvas)

    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (width / 2 - 10).toFloat(),
            paintForClock
        )
    }

    private fun drawCentreCircle(canvas: Canvas) {
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            2F,
            paintForClock
        )
    }

    private fun drawHourArrow(canvas: Canvas) {
        canvas.save()
        canvas.rotate(
            (countHours * 30 + countMin / 60 * 30).toFloat(),
            (width / 2).toFloat(),
            (height / 2).toFloat()
        )
        canvas.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (width / 2).toFloat(),
            (height / 2 - height / 9).toFloat(),
            paintHourArrow
        )
        canvas.restore()
    }

    private fun drawMinuteArrow(canvas: Canvas) {
        canvas.save()
        canvas.rotate(
            360 / 60 * countMin + countSec * 0.1f,
            (width / 2).toFloat(),
            (height / 2).toFloat()
        )
        canvas.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (width / 2).toFloat(),
            (height / 2 - height / 5).toFloat(),
            paintMinuteArrow
        )
        canvas.restore()
    }

    private fun drawSecondArrow(canvas: Canvas) {
        canvas.save()
        canvas.rotate(
            (countSec * 6).toFloat(),
            (width / 2).toFloat(),
            (height / 2).toFloat()
        )
        canvas.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (width / 2).toFloat(),
            (height / 2 - height / 4).toFloat(),
            paintSecArrow
        )
        canvas.restore()
        invalidate()
    }

    private fun setTime() {
        val calendar = Calendar.getInstance()
        countHours = calendar[Calendar.HOUR]
        countMin = calendar[Calendar.MINUTE]
        countSec = calendar[Calendar.SECOND]
    }

    private val paintForClock by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = borderAroundClock
            color = R.color.lilac
            isAntiAlias = true
        }
    }


    private val paintHourArrow by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = 20f
        }
    }

    private val paintMinuteArrow by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            color = Color.BLUE
            strokeWidth = 10f
        }
    }

    private val paintSecArrow by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            color = Color.RED
            strokeWidth = 3f
        }
    }
}