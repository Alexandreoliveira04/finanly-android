package com.finanly.app.core.error

/**
 * Domain/application-level error, free of any infrastructure (Firebase) type.
 * Data layer implementations translate SDK exceptions into these before they
 * reach the Domain or Presentation layers.
 */
sealed class AppError {
    data object NoConnection : AppError()
    data object InvalidCredentials : AppError()
    data object EmailAlreadyInUse : AppError()
    data object WeakPassword : AppError()
    data object UserNotFound : AppError()
    data object PermissionDenied : AppError()
    data class Unknown(val message: String? = null) : AppError()
}