package com.leotorrealba.testapp.data.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double
)

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double,
    val runtime: Int?,
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)
