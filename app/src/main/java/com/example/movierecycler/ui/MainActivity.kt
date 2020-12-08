package com.example.movierecycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.movierecycler.R
import com.example.movierecycler.databinding.ActivityMainBinding
import com.example.movierecycler.ui.add.AddFragment
import com.example.movierecycler.ui.detail.DetailFragment
import com.example.movierecycler.ui.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val listFragment = ListFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.activeFragment, listFragment)
            .commit()
    }

    fun showDetail(movieId: Int) {
//        binding.activeFragment.removeAllViews()

        val detailFragment = DetailFragment.newInstance(movieId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.activeFragment, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    fun showAdd() {
        val addFragment = AddFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activeFragment, addFragment)
            .addToBackStack(null)
            .commit()
    }
}