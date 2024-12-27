package com.leotorrealba.testapp.domain.repository

import com.leotorrealba.testapp.data.model.Movie
import com.leotorrealba.testapp.data.model.MovieDetail

interface DataRepository {
    suspend fun getPopularMovies(page: Int): Result<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetail>
}