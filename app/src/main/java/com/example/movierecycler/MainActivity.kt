package com.example.movierecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.movierecycler.databinding.ActivityMainBinding

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
}