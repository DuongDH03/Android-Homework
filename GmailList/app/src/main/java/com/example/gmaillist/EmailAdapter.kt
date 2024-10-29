package com.example.gmaillist

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emails: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    val color1 = Color(0xFFFFB900)
    val color2 = Color(0xFFD83B01)
    val color3 = Color(0xFFB50E0E)
    val color4 = Color(0xFFE81123)
    val color5 = Color(0xFFB4009E)
    val color6 = Color(0xFF5C2D91)
    val color7 = Color(0xFF0078D7)
    val color8 = Color(0xFF00B4FF)
    val color9 = Color(0xFF008272)
    val color10 = Color(0xFF107C10)

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: TextView = itemView.findViewById(R.id.avatar)
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val emailSnippet: TextView = itemView.findViewById(R.id.email_snippet)
        val timeReceived: TextView = itemView.findViewById(R.id.time_received)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emails[position]
        holder.senderName.text = email.senderName
        holder.emailSnippet.text = email.snippet
        holder.timeReceived.text = email.timeReceived
        holder.avatar.text = email.senderName.first().toString()
        val bgDrawable = holder.avatar.background as GradientDrawable
        bgDrawable.colorFilter = PorterDuffColorFilter(getRandomColor(), PorterDuff.Mode.SRC_IN)
    }

    private fun getRandomColor(): Int {
        val colors = listOf(color1, color2, color3, color4, color5, color6, color7, color8, color9, color10)

        return colors.random().toArgb()
    }

    override fun getItemCount() = emails.size
}


