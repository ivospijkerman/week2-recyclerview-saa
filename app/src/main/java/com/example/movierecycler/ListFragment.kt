package com.example.movierecycler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierecycler.databinding.FragmentListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        Log.i("", "onCreateView: ${Movie.getAll()}")
        val adapter = MovieRecyclerViewAdapter(activity as MainActivity, Movie.getAll())
        binding.movieRecycler.setHasFixedSize(true)
        binding.movieRecycler.layoutManager = LinearLayoutManager(activity)
        binding.movieRecycler.adapter = adapter
//        val adapter = ArrayAdapter(
//            activity!!,
//            android.R.layout.simple_list_item_1,
//            Movie.getAll()
//        )
//        binding.movieList.adapter = adapter
//        binding.movieList.setOnItemClickListener { _, _, position, _ ->
//            val id = Movie.getAll()[position].id
//            Log.i("", "onCreateView: $id")
//            (activity as MainActivity).showDetail(id)
//        }

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