package com.example.movierecycler

import androidx.multidex.MultiDexApplication
import com.example.movierecycler.domain.MovieRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyApplication : MultiDexApplication() {
    private val firestore by lazy { Firebase.firestore }
    val movieRepository by lazy { MovieRepository(firestore.collection("movies")) }
}