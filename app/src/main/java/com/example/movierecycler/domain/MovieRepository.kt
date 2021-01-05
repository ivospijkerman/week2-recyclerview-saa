package com.example.movierecycler.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movierecycler.database.toData
import com.example.movierecycler.database.toMovie
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QueryDocumentSnapshot

class MovieRepository(private val movieCollection: CollectionReference) {
    private val defaultMovie = Movie("Loading", "Loading...", "One moment please", -1)

    private val all: MutableLiveData<List<Movie>> =
        MutableLiveData(listOf(defaultMovie))

    fun getAll(): LiveData<List<Movie>> {
        movieCollection.get()
            .addOnSuccessListener { documents ->
                documents
                    .map(QueryDocumentSnapshot::toMovie)
                    .let(all::postValue)
            }
        return all
    }

    fun getById(id: String): LiveData<Movie> {
        val result = MutableLiveData(defaultMovie)
        movieCollection.document(id).get()
            .addOnSuccessListener { doc ->
                doc.toMovie().let(result::postValue)
            }
        return result
    }

    fun add(movie: Movie) {
        movieCollection.document().set(
            movie.toData()
        ).addOnSuccessListener { getAll() }
    }

    fun remove(movie: Movie) {
        movieCollection.document(movie.id).delete()
            .addOnSuccessListener {
                getAll()
            }
    }
}