package com.example.movierecycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movierecycler.databinding.FragmentDetailBinding

private const val ARG_MOVIE_ID = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    private val movieDetailVM: MovieDetailVM by viewModels {
        val application = requireActivity().application as MyApplication
        val movieRepository = application.movieRepository
        MovieDetailVMFactory(movieRepository, movieId!!)
    }

    private var movieId: Int? = null
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(ARG_MOVIE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val dataObserver = Observer<Movie> {
            binding.movie = movieDetailVM.movie.value
        }
        movieDetailVM.movie.observe(requireActivity(), dataObserver)

        binding.movie = movieDetailVM.movie.value

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DetailFragment.
         */
        @JvmStatic
        fun newInstance(movieId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOVIE_ID, movieId)
                }
            }
    }
}