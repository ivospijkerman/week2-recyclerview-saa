package com.example.movierecycler.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierecycler.*
import com.example.movierecycler.databinding.FragmentListBinding
import com.example.movierecycler.ui.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    private val movieListVM: MovieListVM by viewModels {
        val application = requireActivity().application as MyApplication
        val movieRepository = application.movieRepository
        MovieListVMFactory(movieRepository)
    }

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val mainActivity = activity as MainActivity

        val adapter = MovieRecyclerViewAdapter(mainActivity, movieListVM.allMovies)
        binding.movieRecycler.setHasFixedSize(true)
        binding.movieRecycler.layoutManager = LinearLayoutManager(activity)
        binding.movieRecycler.adapter = adapter

        binding.addButton.setOnClickListener {
            mainActivity.showAdd()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}