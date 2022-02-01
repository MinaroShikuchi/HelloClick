package com.example.hellogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    private var counter : Int = 0
    private var step : Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counterTextView : TextView = findViewById(R.id.textView3)
        val clickMeButton : Button = findViewById(R.id.button2)
        val resetButton : Button = findViewById(R.id.button)
        val reverseButton : Button = findViewById(R.id.button3)
        val normalButton : Button = findViewById(R.id.button4)
        val bigButton : Button = findViewById(R.id.button5)
        val nextButton : View = findViewById(R.id.fab)

        counterTextView.text = counter.toString()

        clickMeButton.setOnClickListener {
            counter = counter.plus(step)
            counterTextView.text = counter.toString()
        }

        resetButton.setOnClickListener {
            counter = 0
            step = 1
            counterTextView.text = counter.toString()
        }

        reverseButton.setOnClickListener {
            step = step.times(-1)
        }

        normalButton.setOnClickListener {
            step = step.sign * 1
        }

        bigButton.setOnClickListener {
            step = step.sign.times(2)
        }

        nextButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }
}