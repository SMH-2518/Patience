package com.example.assignment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Level2Activity : AppCompatActivity() {

    private lateinit var ballView: View
    private lateinit var bgImageView: ImageView
    private lateinit var tvComplete: TextView
    private var pathBitmap: Bitmap? = null
    private var dX = 0f
    private var dY = 0f
    private var startX = 0f
    private var startY = 0f
    private var isGameActive = true
    private var isResetting = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level2)
        ballView = findViewById(R.id.ball_view)
        bgImageView = findViewById(R.id.background_image)
        tvComplete = findViewById(R.id.tvCompleteSign)
        bgImageView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                bgImageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                setupGame()
            }
        })
    }
    private fun setupGame() {
        startX = ballView.x
        startY = ballView.y
        val drawable = bgImageView.drawable as BitmapDrawable
        pathBitmap = drawable.bitmap
        pathBitmap = Bitmap.createScaledBitmap(pathBitmap!!, bgImageView.width, bgImageView.height, true)

        setupTouchListener()
    }
    private fun setupTouchListener() {
        ballView.setOnTouchListener { view, event ->
            if (!isGameActive) return@setOnTouchListener true

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isResetting = false
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    if (isResetting) return@setOnTouchListener true

                    var newX = event.rawX + dX
                    var newY = event.rawY + dY

                    newX = newX.coerceIn(0f, (bgImageView.width - view.width).toFloat())
                    newY = newY.coerceIn(0f, (bgImageView.height - view.height).toFloat())

                    view.x = newX
                    view.y = newY

                    checkGameStatus(newX, newY)
                }
                MotionEvent.ACTION_UP -> {
                }
            }
            true
        }
    }
    private fun checkGameStatus(currentX: Float, currentY: Float) {
        if (currentY < 80) {
            winGame()
            return
        }
        pathBitmap?.let { bitmap ->
            val centerX = (currentX + ballView.width / 2).toInt()
            val centerY = (currentY + ballView.height / 2).toInt()
            if (centerX in 0 until bitmap.width && centerY in 0 until bitmap.height) {
                val pixelColor = bitmap.getPixel(centerX, centerY)
                if (Color.green(pixelColor) < 100) {
                    resetGame()
                }
            }
        }
    }
    private fun resetGame() {
        isResetting = true
        Toast.makeText(this, "Hit the border!", Toast.LENGTH_SHORT).show()
        ballView.x = startX
        ballView.y = startY
    }

    private fun winGame() {
        isGameActive = false
        tvComplete.visibility = View.VISIBLE 
        Toast.makeText(this, "Congratulations!", Toast.LENGTH_LONG).show()
    }
}

