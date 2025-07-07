package com.norman.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.norman.core.di.FavoriteEntryPoint
import com.norman.core.ui.MovieAdapter
import com.norman.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors

class FavoriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val entryPoint = EntryPointAccessors.fromApplication(applicationContext, FavoriteEntryPoint::class.java)

        val factory = FavoriteViewModelFactory(entryPoint.provideUseCase2())
        val favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        val movieAdapter = MovieAdapter()

        favoriteViewModel.favoriteMovie.observe(this) { dataMovie ->
            movieAdapter.setData(dataMovie)
            binding.viewEmpty.root.visibility =
                if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavoriteMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}