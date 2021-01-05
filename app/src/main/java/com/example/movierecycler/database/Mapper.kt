package com.example.movierecycler.database

import com.example.movierecycler.domain.Movie
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toMovie() = Movie(
    id = id,
    title = this["title"] as String,
    director = this["director"] as String,
    releaseYear = this["releaseYear"] as Long
)

fun Movie.toData() = mapOf(
    "title" to this.title,
    "director" to this.director,
    "releaseYear" to this.releaseYear
)