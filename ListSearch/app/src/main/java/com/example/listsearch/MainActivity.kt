package com.example.listsearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var listView: ListView
    private lateinit var searchBar: EditText
    private lateinit var students: List<Student>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var studentNames: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBar = findViewById<EditText>(R.id.search_bar)
        val studentList = findViewById<ListView>(R.id.list_view)

        students = listOf(
            Student("Nguyen Van A", "123456"),
            Student("Tran Thi B", "234567"),
            Student("Le Van C", "345678"),
        )
        studentNames = students.map { "${it.name} - ${it.mssv}" }.toMutableList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
        listView.adapter = adapter
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }
        })
    }
    private fun filterList(query: String) {
        val filteredNames = if (query.length > 2) {
            students.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
            }.map { "${it.name} - ${it.mssv}" }
        } else {
            students.map { "${it.name} - ${it.mssv}" }
        }
        adapter.clear()
        adapter.addAll(filteredNames)
        adapter.notifyDataSetChanged()
    }
}