package com.example.movierecycler

import android.content.Context

class MovieRepository(context: Context) {
    private val movieDao = MovieDao(context)

    fun getAll() = movieDao.getAll()

    fun getById(id: Int): Movie? {
        return getAll().firstOrNull { movie -> movie.id == id }
    }
}