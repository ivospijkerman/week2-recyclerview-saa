package com.example.movierecycler.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movierecycler.domain.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY releaseYear")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Movie?

    @Insert
    suspend fun insert(movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}
/*
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
*/
