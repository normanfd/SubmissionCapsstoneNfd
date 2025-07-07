package com.norman.submissioncapstonenfd.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.norman.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}