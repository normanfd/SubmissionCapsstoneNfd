package com.norman.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.norman.core.domain.usecase.MovieUseCase

class FavoriteViewModelFactory(private val movieUseCase: MovieUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(movieUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}