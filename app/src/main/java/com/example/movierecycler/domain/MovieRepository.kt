package com.example.movierecycler.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movierecycler.database.MovieDao
import com.example.movierecycler.domain.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieRepository(private val movieDao: MovieDao) {
    private val defaultMovie = Movie(-1, "Loading...", "One moment please", -1)

    private val all: MutableLiveData<List<Movie>> =
        MutableLiveData(listOf(defaultMovie))

    fun getAll(): LiveData<List<Movie>> {
        GlobalScope.launch {
            all.postValue(movieDao.getAll())
        }
        return all
    }

    fun getById(id: Int): LiveData<Movie> {
        val result = MutableLiveData(defaultMovie)
        GlobalScope.launch {
            result.postValue(movieDao.getById(id))
        }
        return result
    }

    fun add(movie: Movie) {
        GlobalScope.launch {
            movieDao.insert(movie)
            getAll()
        }
    }
}