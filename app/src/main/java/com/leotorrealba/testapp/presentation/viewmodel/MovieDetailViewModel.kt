package com.leotorrealba.testapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leotorrealba.testapp.data.model.MovieDetail
import com.leotorrealba.testapp.domain.usecase.FetchDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchDataUseCase: FetchDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            fetchDataUseCase.getMovieDetails(movieId)
                .fold(
                    onSuccess = { movie ->
                        _uiState.value = UiState.Success(movie)
                    },
                    onFailure = { error ->
                        _uiState.value = UiState.Error(error.message ?: "Unknown error")
                    }
                )
        }
    }

    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        data class Success(val movie: MovieDetail) : UiState()
        data class Error(val message: String) : UiState()
    }
}
