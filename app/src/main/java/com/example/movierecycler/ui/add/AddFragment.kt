package com.example.movierecycler.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movierecycler.MyApplication
import com.example.movierecycler.R
import com.example.movierecycler.databinding.FragmentAddBinding
import com.example.movierecycler.domain.Movie
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val movieRepository by lazy { (requireActivity().application as MyApplication).movieRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        binding.submitButton.setOnClickListener {
            try {
                val newMovie = Movie(
                    title = binding.addTitleInput.text.toString(),
                    director = binding.addDirectorInput.text.toString(),
                    releaseYear = binding.addReleaseInput.text.toString().toInt()
                )
                movieRepository.add(newMovie)
                requireActivity().supportFragmentManager.popBackStack()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AddFragment.
         */
        @JvmStatic
        fun newInstance() = AddFragment()
    }
}