package com.example.movierecycler

import android.content.ContentValues
import android.content.Context

class MovieDao(context: Context) {
    private val dbHelper = MySQLiteOpenHelper(context)

    fun getAll(): List<Movie> {
        val result = mutableListOf<Movie>()
        dbHelper.readableDatabase.rawQuery("SELECT * FROM $MOVIE_TABLE_NAME", null)
            .use { cursor ->
                while (cursor.moveToNext()) {
                    result.add(
                        Movie(
                            id = cursor.getInt(0),
                            title = cursor.getString(1),
                            director = cursor.getString(2),
                            releaseYear = cursor.getInt(3)
                        )
                    )
                }
                return result
            }
    }

    fun insert(movie: Movie) {
        val values = ContentValues()
        values.put("id", movie.id)
        // nog 3 keer
        dbHelper.writableDatabase.insert(MOVIE_TABLE_NAME, null, values)
    }
}