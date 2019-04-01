package com.example.mytodolist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AuthorCollection(val authorName: String, val books: ArrayList<String> = ArrayList<String>()): Parcelable {

}