package com.tw.openlibrarybooks.data

/**
 * Data class representing a book document from the search response.
 */
data class BookDoc(
    val key: String,
    val title: String,
    val author_name: List<String>? = null,
    val first_publish_year: Int? = null,
    val cover_i: Int? = null
)
