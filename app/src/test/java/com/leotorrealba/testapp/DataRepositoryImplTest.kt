package com.leotorrealba.testapp

import com.leotorrealba.testapp.data.model.Movie
import com.leotorrealba.testapp.data.model.MovieDetail
import com.leotorrealba.testapp.data.model.MovieResponse
import com.leotorrealba.testapp.data.repository.DataRepositoryImpl
import com.leotorrealba.testapp.data.repository.TMDBApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DataRepositoryImplTest {
    private lateinit var repository: DataRepositoryImpl
    private val apiService: TMDBApiService = mockk()

    @Before
    fun setup() {
        repository = DataRepositoryImpl(apiService)
    }

    @Test
    fun `getPopularMovies returns success with movies list when API call is successful`() = runBlocking {
        // Given
        val mockMovies = listOf(
            Movie(1, "Test Movie", "Overview", "/poster.jpg", "2024-01-01", 8.0)
        )
        val mockResponse = MovieResponse(1, mockMovies, 1, 1)

        coEvery { apiService.getPopularMovies(any()) } returns Response.success(mockResponse)

        // When
        val result = repository.getPopularMovies(1)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockMovies, result.getOrNull())
    }

    @Test
    fun `getMovieDetails returns success with movie details when API call is successful`() = runBlocking {
        // Given
        val mockMovie = MovieDetail(1, "Test Movie", "Overview", "/poster.jpg",
            "2024-01-01", 8.0, 120, emptyList())

        coEvery { apiService.getMovieDetails(any()) } returns Response.success(mockMovie)

        // When
        val result = repository.getMovieDetails(1)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockMovie, result.getOrNull())
    }
}
