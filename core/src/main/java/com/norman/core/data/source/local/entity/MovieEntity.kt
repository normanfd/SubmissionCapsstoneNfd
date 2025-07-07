package com.norman.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: String,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String,
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "posterPath")
    val posterPath: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,
    @ColumnInfo(name = "voteCount")
    val voteCount: Int,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)