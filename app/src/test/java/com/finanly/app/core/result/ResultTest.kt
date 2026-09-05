package com.finanly.app.core.result

import com.finanly.app.core.error.AppError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ResultTest {

    @Test
    fun `map transforms the value on Success`() {
        val result: Result<Int> = Result.Success(2)

        val mapped = result.map { it * 10 }

        assertEquals(Result.Success(20), mapped)
    }

    @Test
    fun `map keeps the same Error untouched`() {
        val result: Result<Int> = Result.Error(AppError.NoConnection)

        val mapped = result.map { it * 10 }

        assertEquals(Result.Error(AppError.NoConnection), mapped)
    }

    @Test
    fun `getOrNull returns the value on Success`() {
        val result: Result<String> = Result.Success("finanly")

        assertEquals("finanly", result.getOrNull())
    }

    @Test
    fun `getOrNull returns null on Error`() {
        val result: Result<String> = Result.Error(AppError.UserNotFound)

        assertNull(result.getOrNull())
    }

    @Test
    fun `onSuccess runs only for Success`() {
        var invoked = false
        val result: Result<Int> = Result.Success(1)

        result.onSuccess { invoked = true }

        assertEquals(true, invoked)
    }

    @Test
    fun `onError runs only for Error`() {
        var captured: AppError? = null
        val result: Result<Int> = Result.Error(AppError.InvalidCredentials)

        result.onError { captured = it }

        assertEquals(AppError.InvalidCredentials, captured)
    }
}