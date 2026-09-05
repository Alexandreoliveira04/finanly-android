package com.finanly.app.domain.repository

import com.finanly.app.core.result.Result
import com.finanly.app.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

/**
 * Domain contract for authentication. Implemented in the Data layer against
 * Firebase Authentication; this interface has no knowledge of Firebase.
 */
interface AuthRepository {

    val currentUser: Flow<AuthUser?>

    suspend fun login(email: String, password: String): Result<AuthUser>

    suspend fun register(email: String, password: String): Result<AuthUser>

    suspend fun logout(): Result<Unit>

    suspend fun sendPasswordReset(email: String): Result<Unit>
}