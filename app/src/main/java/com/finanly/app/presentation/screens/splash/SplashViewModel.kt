package com.finanly.app.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finanly.app.domain.usecase.ObserveAuthSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface SplashDestination {
    data object Auth : SplashDestination
    data object Dashboard : SplashDestination
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    observeAuthSession: ObserveAuthSessionUseCase
) : ViewModel() {

    val destination: StateFlow<SplashDestination?> = observeAuthSession()
        .map { user -> if (user != null) SplashDestination.Dashboard else SplashDestination.Auth }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}