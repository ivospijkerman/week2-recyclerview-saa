package com.example.movierecycler.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.movierecycler.MovieDetailVM
import com.example.movierecycler.MovieDetailVMFactory
import com.example.movierecycler.MyApplication
import com.example.movierecycler.R
import com.example.movierecycler.databinding.FragmentDetailBinding
import com.example.movierecycler.domain.Movie

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private val movieRepository by lazy { (requireActivity().application as MyApplication).movieRepository }
    private val args: DetailFragmentArgs by navArgs()

    private val movieDetailVM: MovieDetailVM by navGraphViewModels(R.id.detailFragment) {
        MovieDetailVMFactory(movieRepository, movieId!!)
    }

    private var movieId: String? = null
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = args.movieId
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

        binding.deleteButton.setOnClickListener {
            movieRepository.remove(binding.movie!!)
            findNavController().navigateUp()
//            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.movie = movieDetailVM.movie.value

        return binding.root
    }
}