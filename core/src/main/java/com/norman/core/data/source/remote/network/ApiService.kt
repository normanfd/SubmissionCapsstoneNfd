package com.norman.core.data.source.remote.network

import com.norman.core.data.source.remote.response.PagedMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?language=en-US&page=1&api_key=71a7056b97b7692c9a893720fb319355")
    suspend fun getList(): PagedMovieResponse
}