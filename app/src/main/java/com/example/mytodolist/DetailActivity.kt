package com.example.mytodolist

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.Log
import android.widget.EditText

class DetailActivity : AppCompatActivity() {

    lateinit var authorCollection: AuthorCollection
    lateinit var booksRecyclerView: RecyclerView
    lateinit var addBookFloatingButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        authorCollection = intent.getParcelableExtra(DetailActivity.AUTHOR_COLLECTION_KEY)
        title = authorCollection.authorName
        booksRecyclerView = findViewById(R.id.booksRecyclerView)
        booksRecyclerView.adapter = BooksAdapter(authorCollection.books)
        booksRecyclerView.layoutManager = LinearLayoutManager(this)
        addBookFloatingButton = findViewById(R.id.addBookFloatingButton)
        addBookFloatingButton.setOnClickListener {
            addBook()
        }

    }

    private fun addBook() {
        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog
            .Builder(this)
            .setTitle(R.string.book_name)
            .setView(editText)
            .setPositiveButton(R.string.book_create) { dialog, _ ->
                val bookName = editText.text.toString()
                authorCollection.books.add(bookName)
                (booksRecyclerView.adapter as BooksAdapter).notifyItemInserted(authorCollection.books.size)
                dialog.dismiss()
            }
            .create()
            .show()
    }

    companion object {
        private val AUTHOR_COLLECTION_KEY = "AUTHOR_COLLECTION_KEY"
        const val DETAIL_RESULT_CODE = 123
        const val DETAIL_DATA = "dd"

        fun getIntent(context: Context, authorCollection: AuthorCollection) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(AUTHOR_COLLECTION_KEY, authorCollection)
            }

    }

    override fun onBackPressed() {

        val intent = Intent()
        intent.putExtra(DETAIL_DATA, authorCollection)

        setResult(DETAIL_RESULT_CODE, intent)
        super.onBackPressed()
    }
}
