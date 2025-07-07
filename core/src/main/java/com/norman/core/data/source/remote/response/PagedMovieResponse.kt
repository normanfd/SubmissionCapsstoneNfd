package com.norman.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

class PagedMovieResponse (
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var results: ArrayList<MovieResponse> = arrayListOf(),
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)