package com.example.movierecycler.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val director: String,
    val releaseYear: Int
) {
    override fun toString(): String {
        return "$title ($releaseYear)"
    }
}
