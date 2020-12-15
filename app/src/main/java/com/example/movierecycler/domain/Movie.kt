package com.example.movierecycler.domain

data class Movie(
    val id: String = "Placeholder",
    val title: String,
    val director: String,
    val releaseYear: Long
) {
    override fun toString(): String {
        return "$title ($releaseYear)"
    }
}
