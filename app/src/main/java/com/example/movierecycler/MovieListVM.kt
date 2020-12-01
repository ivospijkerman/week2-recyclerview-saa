package com.example.movierecycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieListVM(repository: MovieRepository) : ViewModel() {
    val allMovies: LiveData<List<Movie>> = repository.getAll()
}

class MovieListVMFactory(
    private val repository: MovieRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(modelClass.isAssignableFrom(MovieListVM::class.java))

        @Suppress("UNCHECKED_CAST")
        return MovieListVM(repository) as T
    }
}