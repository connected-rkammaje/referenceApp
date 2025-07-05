package com.tw.common

/**
 * Sealed class representing the result of a request operation.
 * @param T The type of data returned on success
 */
sealed class RequestResult<out T> {
    /**
     * Represents a loading state
     */
    object Loading : RequestResult<Nothing>()

    /**
     * Represents a successful result with data
     * @param data The successful result data
     */
    data class Success<T>(val data: T) : RequestResult<T>()

    /**
     * Represents an error state
     * @param message The error message
     * @param exception The optional exception that caused the error
     */
    data class Error(val message: String, val exception: Throwable? = null) : RequestResult<Nothing>()

    companion object {
        /**
         * Creates a Success RequestResult with the provided data
         * @param data The data to wrap in a Success state
         * @return RequestResult.Success containing the data
         */
        operator fun <T> invoke(data: T): RequestResult<T> = Success(data)

        /**
         * Executes a suspend function and wraps the result in a RequestResult.
         * Handles exceptions and converts them to Error states.
         * @param block The suspend function to execute
         * @return RequestResult containing either Success with the result or Error
         */
        suspend fun <T> fromResponse(block: suspend () -> T): RequestResult<T> {
            return try {
                val response = block()
                Success(response)
            } catch (e: Exception) {
                Error(e.message ?: "", e)
            }
        }
    }
}
