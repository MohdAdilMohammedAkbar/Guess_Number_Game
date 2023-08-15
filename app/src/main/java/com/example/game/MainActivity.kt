package com.example.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val minNumber = 1
    private val maxNumber = 100
    private val maxAttempts = 5

    private var randomNumber = Random.nextInt(minNumber, maxNumber + 1)
    private var remainingAttempts = maxAttempts
    private lateinit var guessButton: Button
    private lateinit var guessEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var replay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessButton = findViewById(R.id.guessButton)
        guessEditText = findViewById(R.id.guessEditText)
        resultTextView = findViewById(R.id.resultTextView)
        replay = findViewById(R.id.replayButton)

        guessButton.setOnClickListener {
            checkGuess()
        }

        replay.setOnClickListener(){
            recreate()
        }

    }

    private fun checkGuess() {
        val guessedNumber = guessEditText.text.toString().toIntOrNull()

        if (guessedNumber == null) {
            resultTextView.text = "Please enter a valid number."
        } else {
            remainingAttempts--

            if (guessedNumber == randomNumber) {
                resultTextView.text = "Congratulations! You guessed the correct number."
                guessButton.isEnabled = false
            } else if (guessedNumber < randomNumber) {
                resultTextView.text = "Try a higher number. Remaining attempts: $remainingAttempts"
            } else {
                resultTextView.text = "Try a lower number. Remaining attempts: $remainingAttempts"
            }

            if (remainingAttempts == 0) {
                resultTextView.text = "Game over. The correct number was $randomNumber."
                guessButton.isEnabled = false
            }
        }

        guessEditText.text.clear()
    }
}
