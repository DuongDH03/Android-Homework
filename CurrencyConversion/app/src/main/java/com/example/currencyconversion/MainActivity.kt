package com.example.currencyconversion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var originalAmountEditText: EditText
    private lateinit var convertedAmountEditText: EditText
    private lateinit var originalCurrencySpinner: Spinner
    private lateinit var convertedCurrencySpinner: Spinner

    private val conversionRates = mapOf(
        "USD" to mapOf("EUR" to 0.85, "JPY" to 110.0, "GBP" to 0.75, "AUD" to 1.35, "VND" to 23000.0),
        "EUR" to mapOf("USD" to 1.18, "JPY" to 130.0, "GBP" to 0.88, "AUD" to 1.59, "VND" to 27000.0),
        "JPY" to mapOf("USD" to 0.0091, "EUR" to 0.0077, "GBP" to 0.0068, "AUD" to 0.012, "VND" to 210.0),
        "GBP" to mapOf("USD" to 1.34, "EUR" to 1.14, "JPY" to 150.0, "AUD" to 1.81, "VND" to 31000.0),
        "AUD" to mapOf("USD" to 0.74, "EUR" to 0.63, "JPY" to 82.0, "GBP" to 0.55, "VND" to 17300.0),
        "VND" to mapOf("USD" to 0.000043, "EUR" to 0.000037, "JPY" to 0.0047, "GBP" to 0.000032, "AUD" to 0.000058)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        originalAmountEditText = findViewById(R.id.original_amount)
        convertedAmountEditText = findViewById(R.id.converted_amount)
        originalCurrencySpinner = findViewById(R.id.original_currency)
        convertedCurrencySpinner = findViewById(R.id.converted_currency)

        ArrayAdapter.createFromResource(
            this,
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            originalCurrencySpinner.adapter = adapter
            convertedCurrencySpinner.adapter = adapter
        }

        originalAmountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateConversion()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Similarly, add listeners for Spinner selections
        originalCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                updateConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        convertedCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                updateConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun updateConversion() {
        val originalAmount = originalAmountEditText.text.toString().toDoubleOrNull() ?: return
        val originalCurrency = originalCurrencySpinner.selectedItem.toString()
        val convertedCurrency = convertedCurrencySpinner.selectedItem.toString()

        val conversionRate = conversionRates[convertedCurrency]?.get(originalCurrency) ?: return
        val convertedAmount = originalAmount * conversionRate

        convertedAmountEditText.setText(convertedAmount.toString())
    }
}
