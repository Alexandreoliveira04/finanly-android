package com.finanly.app.data.firebase.auth

import com.finanly.app.core.result.Result
import com.finanly.app.data.mapper.toAppError
import com.finanly.app.data.mapper.toAuthUser
import com.finanly.app.domain.model.AuthUser
import com.finanly.app.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override val currentUser: Flow<AuthUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.toAuthUser())
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    override suspend fun login(email: String, password: String): Result<AuthUser> = runCatching {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
            .user?.toAuthUser()
            ?: error("Firebase returned no user after sign in")
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.toAppError()) }
    )

    override suspend fun register(email: String, password: String): Result<AuthUser> = runCatching {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            .user?.toAuthUser()
            ?: error("Firebase returned no user after registration")
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it.toAppError()) }
    )

    override suspend fun logout(): Result<Unit> = runCatching {
        firebaseAuth.signOut()
    }.fold(
        onSuccess = { Result.Success(Unit) },
        onFailure = { Result.Error(it.toAppError()) }
    )

    override suspend fun sendPasswordReset(email: String): Result<Unit> = runCatching {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }.fold(
        onSuccess = { Result.Success(Unit) },
        onFailure = { Result.Error(it.toAppError()) }
    )
}