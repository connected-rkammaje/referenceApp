package com.tw.networking

import com.tw.networking.data.BookDetails
import com.tw.networking.data.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for the Open Library API.
 */
interface OpenLibraryApi {

    /**
     * Search for books by query.
     * @param query The search query
     * @return The search response
     */
    @GET("search.json")
    suspend fun searchBooks(@Query("q") query: String): SearchResponse

    /**
     * Get details for a specific book by ID.
     * @param bookId The Open Library book ID
     * @return The book details
     */
    @GET("books/{bookId}")
    suspend fun getBookDetails(@Path("bookId") bookId: String): BookDetails
}
