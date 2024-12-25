package com.example.a6_memorin

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    val cardWidth = 200
    val cardHeight = 300


    private val catViews = ArrayList<ImageView>()
    private val openedCards = mutableListOf<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val images = listOf(
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8,
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8
        ).shuffled()


        for (i in 0 until 16) {
            catViews.add(
                ImageView(applicationContext).apply {
                    setImageResource(R.drawable.back)
                    layoutParams = LinearLayout.LayoutParams(cardWidth, cardHeight)
                    tag = images[i]
                    setOnClickListener(cardClickListener)
                }
            )
        }

        val rows = Array(4) { LinearLayout(applicationContext).apply { orientation = LinearLayout.HORIZONTAL } }
        for ((count, view) in catViews.withIndex()) {
            rows[count / 4].addView(view)
        }
        for (row in rows) {
            layout.addView(row)
        }

        setContentView(layout)
    }

    private val cardClickListener = View.OnClickListener { view ->
        val imageView = view as ImageView
        val imageResource = imageView.tag as Int
        imageView.setImageResource(imageResource)
        openedCards.add(imageView)

        if (openedCards.size == 2) {
            GlobalScope.launch(Dispatchers.Main) {
                delay(500)
                if (openedCards[0].tag == openedCards[1].tag) {
                    openedCards[0].visibility = View.INVISIBLE
                    openedCards[1].visibility = View.INVISIBLE
                } else {
                    openedCards[0].setImageResource(R.drawable.back)
                    openedCards[1].setImageResource(R.drawable.back)
                }
                openedCards.clear()
                if (checkWin()) {
                    Toast.makeText(applicationContext, "You Win!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun checkWin(): Boolean {
        return catViews.all { it.visibility == View.INVISIBLE }
    }
}
