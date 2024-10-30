package com.example.simplelist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplelist.ui.theme.SimpleListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val inputNumber = findViewById<EditText>(R.id.inputField)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val oddRadio = findViewById<RadioButton>(R.id.oddRadio)
        val evenRadio = findViewById<RadioButton>(R.id.evenRadio)
        val squareRadio = findViewById<RadioButton>(R.id.squareRadio)
        val buttonShow = findViewById<Button>(R.id.showBtn)
        val listView = findViewById<ListView>(R.id.numberListView)
        val errorMessage = findViewById<TextView>(R.id.errorText)

        buttonShow.setOnClickListener {
            val nText = inputNumber.text.toString()

            errorMessage.visibility = TextView.GONE
            if (nText.isEmpty() || nText.toIntOrNull() == null) {
                errorMessage.text = "Please enter positive integer!"
                errorMessage.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            val n = nText.toInt()
            val selectedOption = radioGroup.checkedRadioButtonId
            val result = mutableListOf<String>()

            when (selectedOption){
                R.id.oddRadio -> for (i in 0..n step 2) result.add(i.toString())
                R.id.evenRadio -> for (i in 1 .. n step 2) result.add(i.toString())
                R.id.squareRadio -> for (i in 0..n) {
                    if (i * i <= n) result.add((i*i).toString())
                    else break
                }
                else -> {
                    errorMessage.text = "Please select a valid option."
                    errorMessage.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }
    }

}

