package com.example.mytodolist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class AuthorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val authorIndex = itemView.findViewById<TextView>(R.id.authorIndex)
    val authorName = itemView.findViewById<TextView>(R.id.authorText)


}