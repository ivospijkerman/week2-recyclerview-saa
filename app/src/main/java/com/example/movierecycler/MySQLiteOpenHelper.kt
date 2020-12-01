package com.example.movierecycler

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "my_database"
private const val DB_VERSION = 1
const val MOVIE_TABLE_NAME = "movies"

class MySQLiteOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.apply {
            // Delete table
            execSQL("DROP TABLE IF EXISTS $MOVIE_TABLE_NAME")

            // Create table
            execSQL("CREATE TABLE $MOVIE_TABLE_NAME (" +
                    "id INT PRIMARY KEY NOT NULL, " +
                    "title TEXT NOT NULL, " +
                    "director TEXT NOT NULL, " +
                    "releaseYear INT NOT NULL)"
            )

            // Append table
            execSQL("""INSERT INTO $MOVIE_TABLE_NAME VALUES 
                (5, "The Shawshank Redemption", "F. Darabont", 1994),
                (-100, "The Godfather", "F.F. Coppolla", 1972),
                (9, "The Godfather II", "F.F. Coppolla", 1974)
            """.trimMargin())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            onCreate(db)
        }
    }
}