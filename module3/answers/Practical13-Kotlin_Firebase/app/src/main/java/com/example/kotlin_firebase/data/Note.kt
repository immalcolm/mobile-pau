package com.example.kotlin_firebase.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Note(
    val id: Int,
    val title: String,
    val content: String
) {
    constructor() : this(1, "", "")
}