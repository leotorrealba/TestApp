package com.leotorrealba.testapp.data.repository

import com.leotorrealba.testapp.data.model.Movie
import com.leotorrealba.testapp.data.model.MovieDetail
import com.leotorrealba.testapp.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: TMDBApiService
) : DataRepository {
    override suspend fun getPopularMovies(page: Int): Result<List<Movie>> =
        try {
            val response = apiService.getPopularMovies(page)
            if (response.isSuccessful) {
                Result.success(response.body()?.results ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetail> =
        try {
            val response = apiService.getMovieDetails(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Movie details not found"))
            } else {
                Result.failure(Exception("Error fetching movie details: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
}
