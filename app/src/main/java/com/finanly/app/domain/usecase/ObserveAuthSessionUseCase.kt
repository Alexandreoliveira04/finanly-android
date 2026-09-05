package com.finanly.app.domain.usecase

import com.finanly.app.domain.model.AuthUser
import com.finanly.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Exposes the current session so the Splash screen can decide whether to
 * route to Auth or Dashboard, without depending on the Data layer directly.
 */
class ObserveAuthSessionUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<AuthUser?> = authRepository.currentUser
}