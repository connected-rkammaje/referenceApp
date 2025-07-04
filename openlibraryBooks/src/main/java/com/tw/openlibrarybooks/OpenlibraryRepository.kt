package com.tw.openlibrarybooks

import com.tw.common.RequestResult
import com.tw.networking.Book
import com.tw.networking.OpenLibraryService
import com.tw.networking.toBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Repository class that manages network requests for the Open Library API.
 * This class provides Flow-based APIs for reactive programming and caches results.
 */
class OpenlibraryRepository @Inject constructor(
    private val openLibraryService: OpenLibraryService
) {

    private val _searchResults = MutableStateFlow<RequestResult<List<Book>>>(RequestResult.Success(emptyList()))
    val searchResults: StateFlow<RequestResult<List<Book>>> = _searchResults.asStateFlow()

    private val _bookDetails = MutableStateFlow<RequestResult<Book?>>(RequestResult.Success(null))
    val bookDetails: StateFlow<RequestResult<Book?>> = _bookDetails.asStateFlow()

    /**
     * Search for books by query and update the search results flow.
     * @param query The search query
     */
    suspend fun searchBooks(query: String) {
        if (query.trim().isEmpty()) {
            _searchResults.value = RequestResult.Success(emptyList())
            return
        }

        _searchResults.value = RequestResult.Loading
        val result = openLibraryService.searchBooks(query)
        _searchResults.value = when (result) {
            is RequestResult.Success ->
                RequestResult.Success(result.data.docs.map { doc -> doc.toBook() })
            is RequestResult.Error -> result
            is RequestResult.Loading -> result
        }
    }

    /**
     * Get details for a specific book by ID and update the book details flow.
     * @param bookId The Open Library book ID
     */
    suspend fun getBookDetails(bookId: String) {
        _bookDetails.value = RequestResult.Loading
        val result = openLibraryService.getBookDetails(bookId)
        _bookDetails.value = result
    }

    /**
     * Clear the search results.
     */
    fun clearSearchResults() {
        _searchResults.value = RequestResult.Success(emptyList())
    }

    /**
     * Clear the book details.
     */
    fun clearBookDetails() {
        _bookDetails.value = RequestResult.Success(null)
    }
}
