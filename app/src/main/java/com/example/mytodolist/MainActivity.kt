package com.example.mytodolist

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), AuthorsAdapter.AuthorSelectorClickListener {

    lateinit var authorsRecyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton

    lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataManager = DataManager(this)
        authorsRecyclerView = findViewById(R.id.authorsRecyclerView)
        authorsRecyclerView.layoutManager = LinearLayoutManager(this)
        val authorCollections = dataManager.readCollection()
        authorsRecyclerView.adapter = AuthorsAdapter(authorCollections, this)
        floatingActionButton = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener(View.OnClickListener {
            showAddNewAuthorDialog()
        })
    }

    override fun authorClicked(authorCollection: AuthorCollection) {
        showDetail(authorCollection)
    }

    private fun showAddNewAuthorDialog() {
        val title = getString(R.string.author_name)
        val positiveButton = getString(R.string.create_list)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        alertDialogBuilder.setPositiveButton(positiveButton) { dialog, _ ->
            val authorName =  editText.text.toString()
            val authorCollection = AuthorCollection(authorName)
            dataManager.saveCollection(authorCollection)

            (authorsRecyclerView.adapter as AuthorsAdapter).add(authorCollection)
            dialog.dismiss()
            showDetail(authorCollection)
        }

        alertDialogBuilder.setView(editText)
        alertDialogBuilder.create().show()
    }

    private fun showDetail(authorCollection: AuthorCollection) {
        val intent = DetailActivity.getIntent(this, authorCollection)
        startActivityForResult(intent, DetailActivity.DETAIL_RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DetailActivity.DETAIL_RESULT_CODE) {
            data?.let {
                val collection = data.getParcelableExtra<AuthorCollection>(DetailActivity.DETAIL_DATA)
                dataManager.saveCollection(collection)
                updateCollection()

            }
        }
    }

    private fun updateCollection() {
        val collections = dataManager.readCollection()
        authorsRecyclerView.adapter = AuthorsAdapter(collections, this)
    }

}
