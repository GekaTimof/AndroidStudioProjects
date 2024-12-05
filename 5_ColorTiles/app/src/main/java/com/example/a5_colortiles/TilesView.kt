package com.example.a5_colortiles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View

class TilesView(ctx: Context) : View(ctx) {
    val field: Array<BooleanArray> = Array(4){BooleanArray(4) {false}}// инициализация массива
    var h = 1000; var w = 1000 // значения по умолчанию, будут заменены в onLayout()
    val paint = Paint() // краска, цвет и стиль отрисовки

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = bottom - top; w = right - left
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.GRAY
        paint.style = Paint.Style.STROKE
        for (row: Int in 0..3) {
            for (column: Int in 0..3)
            {
                canvas.drawRect(90F * column, 90F * row, 90F * column + 90, 90F * row + 90, paint )
                Log.d("mytag", "%f, %f, %f, %f".format(30F * column, 30F * row, 30F * column + 30, 30F * row + 30, paint ))
            }
        }
    }
}