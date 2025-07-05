package com.tw.openlibrarybooks.data

/**
 * Data class representing a search response from the Open Library API.
 */
data class SearchResponse(
    val numFound: Int,
    val start: Int,
    val docs: List<BookDoc>
)
