package com.finanly.app.core.ui

import com.finanly.app.core.error.AppError

/**
 * Standard state shape for screens that load data, so every ViewModel exposes
 * the same Loading/Success/Empty/Error contract expected by the Compose screens.
 */
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data object Empty : UiState<Nothing>()
    data class Error(val error: AppError) : UiState<Nothing>()
}