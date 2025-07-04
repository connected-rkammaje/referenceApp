package com.tw.openlibrarybooks.data

/**
 * Data class representing detailed book information.
 */
data class BookDetails(
    val key: String,
    val title: String,
    val authors: List<Author>? = null,
    val publish_date: String? = null,
    val covers: List<Int>? = null,
    val description: String? = null
)
