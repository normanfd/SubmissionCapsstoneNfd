package com.norman.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.norman.core.domain.usecase.MovieUseCase

class FavoriteViewModel (movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}