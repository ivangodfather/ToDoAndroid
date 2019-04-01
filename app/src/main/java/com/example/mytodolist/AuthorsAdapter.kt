package com.example.mytodolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AuthorsAdapter(val authorCollections: ArrayList<AuthorCollection>, val clickListener: AuthorSelectorClickListener) : RecyclerView.Adapter<AuthorViewHolder>() {

    interface AuthorSelectorClickListener {
        fun authorClicked(authorCollection: AuthorCollection)
    }

    override fun getItemCount(): Int {
        return authorCollections.size
    }

    override fun onBindViewHolder(viewHolder: AuthorViewHolder, index: Int) {
        val authorCollection = authorCollections[index]
        viewHolder.authorIndex.text = index.toString()
        viewHolder.authorName.text = authorCollection.authorName
        viewHolder.itemView.setOnClickListener( {
            clickListener.authorClicked(authorCollections.get(index))
        })
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AuthorViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.author_view_holder, p0, false)
        return AuthorViewHolder(view)
    }

    fun add(authorCollection: AuthorCollection) {
        authorCollections.add(authorCollection)
        notifyDataSetChanged()
    }

}