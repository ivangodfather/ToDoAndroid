package com.example.mytodolist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val bookNameTextView = itemView.findViewById<TextView>(R.id.bookName)
}