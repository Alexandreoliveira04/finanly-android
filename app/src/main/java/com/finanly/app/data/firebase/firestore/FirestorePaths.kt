package com.finanly.app.data.firebase.firestore

/**
 * Root Firestore collection paths. Every user document lives under
 * users/{uid}/..., matching the security rules that scope access to the
 * authenticated owner. Extended as future modules add their own subcollections.
 */
object FirestorePaths {
    const val USERS = "users"
}