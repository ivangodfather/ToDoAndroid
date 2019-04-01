package com.example.mytodolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class BooksAdapter(val books: ArrayList<String>): RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book_view_holder, parent, false)
        return BookViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(bookViewHolder: BookViewHolder, index: Int) {
        val book = books[index]
        bookViewHolder.bookNameTextView.text = book
    }

}