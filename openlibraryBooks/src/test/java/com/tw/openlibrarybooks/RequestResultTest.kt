package com.tw.openlibrarybooks

import com.tw.common.RequestResult
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

/**
 * Test class for RequestResult sealed class functionality
 */
class RequestResultTest {

    @Test
    fun `RequestResult Loading state should be correct type`() {
        val result = RequestResult.Loading
        assertTrue(result is RequestResult.Loading)
        assertFalse(result is RequestResult.Success<*>)
        assertFalse(result is RequestResult.Error)
    }

    @Test
    fun `RequestResult Success state should contain data`() {
        val testData = listOf("test1", "test2")
        val result = RequestResult.Success(testData)

        assertTrue(result is RequestResult.Success)
        assertEquals(testData, result.data)
        assertFalse(result is RequestResult.Loading)
        assertFalse(result is RequestResult.Error)
    }

    @Test
    fun `RequestResult Error state should contain message`() {
        val errorMessage = "Test error message"
        val exception = RuntimeException("Test exception")
        val result = RequestResult.Error(errorMessage, exception)

        assertTrue(result is RequestResult.Error)
        assertEquals(errorMessage, result.message)
        assertEquals(exception, result.exception)
        assertFalse(result is RequestResult.Loading)
        assertFalse(result is RequestResult.Success<*>)
    }

    @Test
    fun `RequestResult Error state can work without exception`() {
        val errorMessage = "Test error message"
        val result = RequestResult.Error(errorMessage)

        assertTrue(result is RequestResult.Error)
        assertEquals(errorMessage, result.message)
        assertNull(result.exception)
    }

    @Test
    fun `RequestResult fromResponse should return Success when block executes successfully`() = runBlocking {
        val testData = "Test successful response"
        val result = RequestResult.fromResponse {
            testData
        }

        assertTrue(result is RequestResult.Success)
        assertEquals(testData, (result as RequestResult.Success).data)
    }

    @Test
    fun `RequestResult fromResponse should return Error when block throws exception`() = runBlocking {
        val exceptionMessage = "Test exception"
        val result = RequestResult.fromResponse {
            throw RuntimeException(exceptionMessage)
        }

        assertTrue(result is RequestResult.Error)
        assertEquals(exceptionMessage, (result as RequestResult.Error).message)
        assertNotNull(result.exception)
        assertTrue(result.exception is RuntimeException)
    }
}
