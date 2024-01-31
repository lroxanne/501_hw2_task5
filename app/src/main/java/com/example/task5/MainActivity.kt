package com.example.task5
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Button


import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    var firstNum: Double? = null
    var currentOperator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.dot
        )

        numberButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { view ->
                onNumberButtonClicked(view)
            }
        }

        findViewById<Button>(R.id.addition).setOnClickListener {
            operatorClicked("+")
        }

        findViewById<Button>(R.id.substarction).setOnClickListener {
            operatorClicked("-")
        }

        findViewById<Button>(R.id.multiply).setOnClickListener {
            operatorClicked("*")
        }

        findViewById<Button>(R.id.divison).setOnClickListener {
            operatorClicked("/")
        }

        findViewById<Button>(R.id.sqrt).setOnClickListener {
            calculateSquareRoot()
        }

        findViewById<Button>(R.id.equation).setOnClickListener {
            calculateResult()
        }
    }

     fun operatorClicked(operator: String) {
        firstNum = editText.text.toString().toDoubleOrNull()
        if (firstNum == null) {
            Toast.makeText(this, "Enter a valid number first", Toast.LENGTH_SHORT).show()
            return
        }
        currentOperator = operator
        editText.text.clear()
    }

    fun onNumberButtonClicked(view: View) {
        if (view is Button) {
            // Append the number to the editText
            editText.append(view.text)
        }
    }

     fun calculateSquareRoot() {
        val number = editText.text.toString().toDoubleOrNull()
        if (number == null || number < 0) {
            Toast.makeText(this, "Enter a valid non-negative number", Toast.LENGTH_SHORT).show()
            return
        }
        editText.setText(Math.sqrt(number).toString())
    }

     fun calculateResult() {
        val secondNumber = editText.text.toString().toDoubleOrNull()
        if (firstNum == null || secondNumber == null) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (currentOperator) {
            "+" -> firstNum!! + secondNumber
            "-" -> firstNum!! - secondNumber
            "*" -> firstNum!! * secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero.", Toast.LENGTH_SHORT).show()
                    return
                } else {
                    firstNum!! / secondNumber
                }
            }
            else -> null
        }

        result?.let {
            editText.setText(it.toString())
        }


        firstNum = null
        currentOperator = null
    }
}


