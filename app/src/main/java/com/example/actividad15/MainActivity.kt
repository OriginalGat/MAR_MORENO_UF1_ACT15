package com.example.actividad15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var currentInput = ""
    private var operator = ""
    private var previousInput = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resultTextView = findViewById(R.id.textView)

        // Números
        val button0: Button = findViewById(R.id.button17)
        val button1: Button = findViewById(R.id.button16)
        val button2: Button = findViewById(R.id.button25)
        val button3: Button = findViewById(R.id.button26)
        val button4: Button = findViewById(R.id.button15)
        val button5: Button = findViewById(R.id.button22)
        val button6: Button = findViewById(R.id.button23)
        val button7: Button = findViewById(R.id.button14)
        val button8: Button = findViewById(R.id.button19)
        val button9: Button = findViewById(R.id.button20)

        // Operaciones
        val buttonSum: Button = findViewById(R.id.button30)
        val buttonSubtract: Button = findViewById(R.id.button27)
        val buttonMultiply: Button = findViewById(R.id.button24)
        val buttonDivide: Button = findViewById(R.id.button21)
        val buttonEqual: Button = findViewById(R.id.button29)
        val buttonClear: Button = findViewById(R.id.button11)
        val buttonCE: Button = findViewById(R.id.button12)
        val buttonDel: Button = findViewById(R.id.button13)
        val buttonDot: Button = findViewById(R.id.button28)
        val buttonPercentage: Button = findViewById(R.id.button10)

        // Eventos para los números
        val numberButtons = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onNumberClicked(index.toString())
            }
        }

        // Eventos para las operaciones
        buttonSum.setOnClickListener { onOperatorClicked("+") }
        buttonSubtract.setOnClickListener { onOperatorClicked("-") }
        buttonMultiply.setOnClickListener { onOperatorClicked("*") }
        buttonDivide.setOnClickListener { onOperatorClicked("/") }
        buttonEqual.setOnClickListener { onEqualClicked() }
        buttonClear.setOnClickListener { onClearClicked() }
        buttonCE.setOnClickListener { onCEClicked() }
        buttonDel.setOnClickListener { ondelClicked() }
        buttonDot.setOnClickListener { onDotClicked() }
        buttonPercentage.setOnClickListener { onPercentageClicked() }
    }

    private fun onNumberClicked(number: String) {
        currentInput += number
        resultTextView.text = currentInput
    }

    private fun onOperatorClicked(op: String) {
        if (currentInput.isNotEmpty()) {
            previousInput = currentInput
            currentInput = ""
            operator = op
        }
    }

    private fun onEqualClicked() {
        if (previousInput.isNotEmpty() && currentInput.isNotEmpty()) {
            val result = calculate(previousInput, operator, currentInput)
            resultTextView.text = result.toString()
            currentInput = result.toString()
            previousInput = ""
            operator = ""
        }
    }

    private fun onClearClicked() {
        currentInput = ""
        previousInput = ""
        operator = ""
        resultTextView.text = "0"
    }

    private fun onCEClicked() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            resultTextView.text = currentInput.ifEmpty { "0" }
        }
    }

    private fun ondelClicked() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            resultTextView.text = currentInput.ifEmpty { "0" }
        }
    }

    private fun onDotClicked() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            resultTextView.text = currentInput
        }
    }

    private fun onPercentageClicked() {
        if (currentInput.isNotEmpty()) {
            val percentage = currentInput.toDouble() / 100
            currentInput = percentage.toString()
            resultTextView.text = currentInput
        }
    }

    private fun calculate(num1: String, operator: String, num2: String): Double {
        val n1 = num1.toDouble()
        val n2 = num2.toDouble()

        return when (operator) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> if (n2 != 0.0) n1 / n2 else Double.NaN
            else -> 0.0
        }
    }
}


