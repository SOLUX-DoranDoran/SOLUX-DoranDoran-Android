package com.solux.dorandoran.util

sealed interface UiState<out T> {
    data object Empty : UiState<Nothing>

    data object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : UiState<T>

    data class Failure(
        val msg: String,
    ) : UiState<Nothing>

    fun getUiStateModel(): UiStateModel {
        return UiStateModel(
            this is Empty,
            this is Loading,
            this is Success,
            this is Failure
        )
    }
}

data class UiStateModel(
    val isEmpty: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false
)