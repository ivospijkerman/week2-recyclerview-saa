package com.example.movierecycler

data class Movie(
    val id: Int,
    val title: String,
    val director: String,
    val releaseYear: Int
) {
    override fun toString(): String {
        return "$title ($releaseYear)"
    }
}
