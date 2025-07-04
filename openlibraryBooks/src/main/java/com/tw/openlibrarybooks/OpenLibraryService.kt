package com.tw.openlibrarybooks

import com.tw.common.RequestResult
import com.tw.openlibrarybooks.data.BookDetails
import com.tw.openlibrarybooks.data.BookDoc
import com.tw.openlibrarybooks.data.SearchResponse
import javax.inject.Inject

/**
 * Service class for interacting with the Open Library API.
 * This class provides methods to search for books and retrieve book details.
 */
class OpenLibraryService @Inject constructor(
    private val api: OpenLibraryApi
) {

    /**
     * Search for books by query.
     * @param query The search query
     * @return RequestResult containing either loading, success with search response, or error
     */
    suspend fun searchBooks(query: String): RequestResult<SearchResponse> = RequestResult.fromResponse { api.searchBooks(query) }

    /**
     * Get details for a specific book by ID.
     * @param bookId The Open Library book ID
     * @return RequestResult containing either loading, success with book details, or error
     */
    suspend fun getBookDetails(bookId: String): RequestResult<Book> = RequestResult.fromResponse { api.getBookDetails(bookId).toBook() }
}

/**
 * Extension function to convert BookDetails to Book.
 */
fun BookDetails.toBook(): Book {
    return Book(
        id = key,
        title = title,
        author = authors?.firstOrNull()?.name,
        publishYear = publish_date?.takeLast(4)?.toIntOrNull(),
        coverUrl = covers?.firstOrNull()?.let { coverId -> 
            "https://covers.openlibrary.org/b/id/$coverId-L.jpg" 
        }
    )
}

/**
 * Extension function to convert BookDoc to Book.
 */
fun BookDoc.toBook(): Book {
    return Book(
        id = key,
        title = title,
        author = author_name?.firstOrNull(),
        publishYear = first_publish_year,
        coverUrl = cover_i?.let { coverId -> 
            "https://covers.openlibrary.org/b/id/$coverId-L.jpg" 
        }
    )
}

/**
 * Data class representing a book from the Open Library API.
 */
data class Book(
    val id: String,
    val title: String,
    val author: String? = null,
    val publishYear: Int? = null,
    val coverUrl: String? = null
)
