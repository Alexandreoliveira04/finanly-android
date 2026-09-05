package com.finanly.app.data.mapper

import com.finanly.app.core.error.AppError
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestoreException

/**
 * Translates Firebase SDK exceptions into [AppError], so no Firebase type ever
 * leaks past the Data layer.
 */
fun Throwable.toAppError(): AppError = when (this) {
    is FirebaseNetworkException -> AppError.NoConnection
    is FirebaseAuthWeakPasswordException -> AppError.WeakPassword
    is FirebaseAuthUserCollisionException -> AppError.EmailAlreadyInUse
    is FirebaseAuthInvalidUserException -> AppError.UserNotFound
    is FirebaseAuthInvalidCredentialsException -> AppError.InvalidCredentials
    is FirebaseAuthException -> AppError.Unknown(message)
    is FirebaseFirestoreException ->
        if (code == FirebaseFirestoreException.Code.PERMISSION_DENIED) {
            AppError.PermissionDenied
        } else {
            AppError.Unknown(message)
        }
    else -> AppError.Unknown(message)
}