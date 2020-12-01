package com.example.movierecycler

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val director: String,
    val releaseYear: Int
) {
    override fun toString(): String {
        return "$title ($releaseYear)"
    }
}
