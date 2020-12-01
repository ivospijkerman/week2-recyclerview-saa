package com.example.movierecycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailVM(
    repository: MovieRepository,
    movieId: Int
) : ViewModel() {
    val movie: LiveData<Movie> = repository.getById(movieId)

}

class MovieDetailVMFactory(
    private val repository: MovieRepository,
    private val movieId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(modelClass.isAssignableFrom(MovieDetailVM::class.java))

        @Suppress("UNCHECKED_CAST")
        return MovieDetailVM(repository, movieId) as T
    }
}