package com.example.mytodolist

import android.content.Context
import android.preference.PreferenceManager

class DataManager(val context: Context) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveCollection(collection: AuthorCollection) {
        val editSharedPreferences = sharedPreferences.edit()
        editSharedPreferences.putStringSet(collection.authorName, collection.books.toHashSet())
        editSharedPreferences.apply()
    }

    fun readCollection(): ArrayList<AuthorCollection> {
        val contents = sharedPreferences.all
        var authorCollections = ArrayList<AuthorCollection>()
        for (item in contents) {
            val itemsHashSet = item.value as HashSet<String>
            val authorCollection = AuthorCollection(item.key, ArrayList(itemsHashSet))
            authorCollections.add(authorCollection)
        }
        return authorCollections
    }
}