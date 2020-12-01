package com.example.movierecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView


class MovieRecyclerViewAdapter(
    private val activity: MainActivity,
    private val movies: LiveData<List<Movie>>
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    init {
        val dataObserver = Observer<List<Movie>> {
            this.notifyDataSetChanged()
        }

        movies.observe(activity, dataObserver)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleText)
        var id: Int? = null

        init {
            view.setOnClickListener {
                val id = id ?: throw IllegalStateException()
                activity.showDetail(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.view_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("", "onBindViewHolder: $position")
        val movie = movies.value?.get(position) ?: throw IllegalStateException()

        holder.id = movie.id
        holder.titleText.text = movie.title
    }

    override fun getItemCount(): Int = movies.value?.size ?: 0

}