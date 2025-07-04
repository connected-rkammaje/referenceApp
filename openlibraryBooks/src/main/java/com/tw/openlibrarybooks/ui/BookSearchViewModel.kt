package com.tw.openlibrarybooks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.common.RequestResult
import com.tw.common.ResourceProvider
import com.tw.networking.Book
import com.tw.openlibrarybooks.OpenlibraryRepository
import com.tw.openlibrarybooks.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Data class representing the UI state for the BookSearchScreen.
 */
data class BookSearchViewState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val errorMessage: String? = null
)

/**
 * ViewModel for managing book search state and operations.
 */
@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val openlibraryRepository: OpenlibraryRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    companion object {
        private const val SUBSCRIPTION_TIMEOUT_MILLIS = 5000L
    }

    /**
     * Combined view state that contains all UI-related state.
     */
    val viewState: StateFlow<BookSearchViewState> = combine(
        _searchQuery.asStateFlow(),
        openlibraryRepository.searchResults
    ) { searchQuery: String, searchResult: RequestResult<List<Book>> ->
        BookSearchViewState(
            searchQuery = searchQuery,
            isLoading = searchResult is RequestResult.Loading,
            books = when (searchResult) {
                is RequestResult.Success -> searchResult.data
                else -> emptyList()
            },
            errorMessage = when (searchResult) {
                is RequestResult.Error -> {
                    if (searchResult.message.isNotEmpty()) {
                        resourceProvider.getString(R.string.request_failed, searchResult.message)
                    } else {
                        resourceProvider.getString(R.string.unknown_error)
                    }
                }
                else -> null
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(SUBSCRIPTION_TIMEOUT_MILLIS),
        initialValue = BookSearchViewState()
    )

    /**
     * Updates the search query.
     */
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    /**
     * Searches for books based on the current query.
     */
    fun searchBooks() {
        val query = _searchQuery.value.trim()
        viewModelScope.launch {
            openlibraryRepository.searchBooks(query)
        }
    }

    /**
     * Clears the error state and resets to empty results.
     */
    fun clearError() {
        openlibraryRepository.clearSearchResults()
    }
}
