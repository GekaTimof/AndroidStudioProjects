package com.example.a5_colortiles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class TilesView(ctx: Context) : View(ctx) {
    private val field: Array<BooleanArray> = Array(4) { BooleanArray(4) { Random.nextBoolean() } }

    private val paint = Paint()
    private var h = 0
    private var w = 0

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = bottom - top
        w = right - left
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val tileSize = (w / 4).toFloat()

        for (row in 0..3) {
            for (col in 0..3) {
                paint.color = if (field[row][col]) Color.LTGRAY else Color.DKGRAY
                paint.style = Paint.Style.FILL
                canvas.drawRect(
                    col * tileSize, row * tileSize,
                    (col + 1) * tileSize, (row + 1) * tileSize,
                    paint
                )

                paint.color = Color.BLACK
                paint.style = Paint.Style.STROKE
                canvas.drawRect(
                    col * tileSize, row * tileSize,
                    (col + 1) * tileSize, (row + 1) * tileSize,
                    paint
                )
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val tileSize = (w / 4).toFloat()
            val col = (event.x / tileSize).toInt()
            val row = (event.y / tileSize).toInt()

            if (row in 0..3 && col in 0..3) {
                toggleColors(row, col)
                invalidate()
                if (checkWin()) {
                    Toast.makeText(context, "You Win!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return true
    }

    private fun toggleColors(row: Int, col: Int) {
        for (i in 0..3) {
            field[row][i] = !field[row][i] // Toggle row
            field[i][col] = !field[i][col] // Toggle column
        }
        field[row][col] = !field[row][col] // Revert center toggle
    }

    private fun checkWin(): Boolean {
        val firstTile = field[0][0]
        for (row in field) {
            for (tile in row) {
                if (tile != firstTile) return false
            }
        }
        return true
    }


}