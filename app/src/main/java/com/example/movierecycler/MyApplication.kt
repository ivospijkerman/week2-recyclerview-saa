package com.example.movierecycler

import android.app.Application
import com.example.movierecycler.database.MyRoomDatabase
import com.example.movierecycler.domain.MovieRepository

class MyApplication : Application() {
    val database by lazy {  MyRoomDatabase.getInstance(this) }
    val movieRepository by lazy { MovieRepository(database.movieDao()) }
}