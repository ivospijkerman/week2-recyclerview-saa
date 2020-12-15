package com.example.movierecycler.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.movierecycler.R
import com.example.movierecycler.databinding.ActivityMainBinding
import com.example.movierecycler.ui.add.AddFragment
import com.example.movierecycler.ui.detail.DetailFragment
import com.example.movierecycler.ui.list.ListFragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

private const val RC_SIGN_IN = 1234

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (FirebaseAuth.getInstance().currentUser == null) {
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(), RC_SIGN_IN
            )

        } else {
            val listFragment = ListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.activeFragment, listFragment)
                .commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                recreate()
            } else {
                finishAffinity()
            }
        }
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