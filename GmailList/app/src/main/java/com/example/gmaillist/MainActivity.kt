package com.example.gmaillist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Email(val senderName: String, val snippet: String, val timeReceived: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val emails = listOf( Email("Alice Johnson", "Hey, how are you?", "10:30 AM"),
            Email("Bob Smith", "Meeting tomorrow", "9:15 AM"),
            Email("Catherine Lee", "Project update", "Yesterday"),
            Email("Deez Brown", "Do you know Ligma?", "2 days ago"),
            Email("Eva Adams", "Urgent: Please read", "1 week ago") )

        recyclerView.adapter = EmailAdapter(emails)
    }
}
