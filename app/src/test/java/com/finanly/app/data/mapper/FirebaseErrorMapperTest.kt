package com.finanly.app.data.mapper

import com.finanly.app.core.error.AppError
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import org.junit.Assert.assertEquals
import org.junit.Test

class FirebaseErrorMapperTest {

    @Test
    fun `network exception maps to NoConnection`() {
        val error = FirebaseNetworkException("offline").toAppError()

        assertEquals(AppError.NoConnection, error)
    }

    @Test
    fun `weak password exception maps to WeakPassword`() {
        val error = FirebaseAuthWeakPasswordException("ERROR_WEAK_PASSWORD", "message", "reason").toAppError()

        assertEquals(AppError.WeakPassword, error)
    }

    @Test
    fun `user collision exception maps to EmailAlreadyInUse`() {
        val error = FirebaseAuthUserCollisionException("ERROR_EMAIL_ALREADY_IN_USE", "message").toAppError()

        assertEquals(AppError.EmailAlreadyInUse, error)
    }

    @Test
    fun `invalid user exception maps to UserNotFound`() {
        val error = FirebaseAuthInvalidUserException("ERROR_USER_NOT_FOUND", "message").toAppError()

        assertEquals(AppError.UserNotFound, error)
    }

    @Test
    fun `invalid credentials exception maps to InvalidCredentials`() {
        val error = FirebaseAuthInvalidCredentialsException("ERROR_WRONG_PASSWORD", "message").toAppError()

        assertEquals(AppError.InvalidCredentials, error)
    }

    @Test
    fun `unknown exception maps to Unknown`() {
        val error = IllegalStateException("boom").toAppError()

        assertEquals(AppError.Unknown("boom"), error)
    }
}