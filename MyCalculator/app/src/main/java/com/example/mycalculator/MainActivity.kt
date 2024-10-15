package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var operationTextView: TextView
    private val calculator = Calculator()
    private var currentInput = ""
    private var operator: String? = null
    private var operand1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linear_activity_main)

        resultTextView = findViewById(R.id.result)
        operationTextView = findViewById(R.id.operation)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide,
            R.id.buttonEqual, R.id.buttonC, R.id.buttonCE, R.id.buttonBS
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it as Button) }
        }
    }

    private fun onButtonClick(button: Button) {
        when (val text = button.text.toString()) {
            in "0".."9" -> {
                currentInput += text
                resultTextView.text = currentInput
            }
            "+", "-", "x", "/" -> {
                operator = text
                operand1 = currentInput.toIntOrNull()
                currentInput = ""
                operationTextView.text = "$operand1 $operator"
            }
            "=" -> {
                val operand2 = currentInput.toIntOrNull()
                if (operand1 != null && operand2 != null && operator != null) {
                    val result = when (operator) {
                        "+" -> calculator.add(operand1!!, operand2)
                        "-" -> calculator.subtract(operand1!!, operand2)
                        "x" -> calculator.multiply(operand1!!, operand2)
                        "/" -> calculator.divide(operand1!!, operand2)
                        else -> 0
                    }
                    resultTextView.text = result.toString()
                    operationTextView.text = "$operand1 $operator $operand2"
                    currentInput = result.toString()
                    operator = null
                    operand1 = null
                }
            }
            "C" -> {
                currentInput = ""
                operator = null
                operand1 = null
                resultTextView.text = "0"
                operationTextView.text = ""
            }
            "CE" -> {
                currentInput = ""
                resultTextView.text ="0"
            }
            "BS" -> {
                if (currentInput.isNotEmpty()) {
                    currentInput = currentInput.dropLast(1)
                    resultTextView.text = if (currentInput.isEmpty()) "0" else currentInput
                }
            }
        }
    }
}
