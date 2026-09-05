package com.finanly.app.data.mapper

import com.finanly.app.domain.model.AuthUser
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toAuthUser(): AuthUser = AuthUser(uid = uid, email = email)