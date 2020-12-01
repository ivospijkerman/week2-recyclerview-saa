package com.example.movierecycler

import android.app.Application

class MyApplication : Application() {
    val database by lazy {  MyRoomDatabase.getInstance(this) }
    val movieRepository by lazy { MovieRepository(database.movieDao()) }
}