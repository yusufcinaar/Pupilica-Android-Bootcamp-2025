package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var currentNumber = 0
    private var previousNumber = 0
    private var isNewNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Sayı tuşlarını ayarla
        setupNumberButton(R.id.button0, 0)
        setupNumberButton(R.id.button1, 1)
        setupNumberButton(R.id.button2, 2)
        setupNumberButton(R.id.button3, 3)
        setupNumberButton(R.id.button4, 4)
        setupNumberButton(R.id.button5, 5)
        setupNumberButton(R.id.button6, 6)
        setupNumberButton(R.id.button7, 7)
        setupNumberButton(R.id.button8, 8)
        setupNumberButton(R.id.button9, 9)

        // Toplama tuşunu ayarla
        findViewById<Button>(R.id.buttonPlus).setOnClickListener {
            previousNumber = currentNumber
            isNewNumber = true
            updateDisplay()
        }

        // Sıfırlama tuşunu ayarla
        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            currentNumber = 0
            previousNumber = 0
            isNewNumber = true
            updateDisplay()
        }
    }

    private fun setupNumberButton(buttonId: Int, number: Int) {
        findViewById<Button>(buttonId).setOnClickListener {
            if (isNewNumber) {
                currentNumber = number
                isNewNumber = false
            } else {
                currentNumber = currentNumber * 10 + number
            }
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        resultTextView.text = if (isNewNumber) {
            (previousNumber + currentNumber).toString()
        } else {
            currentNumber.toString()
        }
    }
}
