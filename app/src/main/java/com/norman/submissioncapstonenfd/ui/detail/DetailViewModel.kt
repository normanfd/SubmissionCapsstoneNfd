package com.norman.submissioncapstonenfd.ui.detail

import androidx.lifecycle.ViewModel
import com.norman.core.domain.model.Movie
import com.norman.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) = movieUseCase.setFavoriteMovie(movie, newStatus)
}