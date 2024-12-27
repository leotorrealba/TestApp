package com.leotorrealba.testapp.domain.usecase

import com.leotorrealba.testapp.domain.repository.DataRepository
import javax.inject.Inject

class FetchDataUseCase @Inject constructor(
    private val repository: DataRepository
) {
    suspend fun getPopularMovies(page: Int) = repository.getPopularMovies(page)

    suspend fun getMovieDetails(movieId: Int) = repository.getMovieDetails(movieId)
}
