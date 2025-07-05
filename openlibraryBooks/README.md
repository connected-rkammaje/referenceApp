# Open Library Books Module

This Android library module provides an interface to the [Open Library API](https://openlibrary.org/developers/api) for searching and retrieving book information.

## Features

- Search for books by query
- Retrieve detailed information about a specific book
- Handle book cover images

## Usage

### Setup

Add the module to your project by including it in your `settings.gradle.kts`:

```kotlin
include(":openlibraryBooks")
```

And add the dependency in your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(project(":openlibraryBooks"))
}
```

### Searching for Books

```kotlin
val openLibraryService = OpenLibraryService()

// Search for books
val books = openLibraryService.searchBooks("android programming")

// Process the results
books.forEach { book ->
    println("Title: ${book.title}")
    println("Author: ${book.author ?: "Unknown"}")
    println("Published: ${book.publishYear ?: "Unknown"}")
    println("Cover URL: ${book.coverUrl ?: "No cover available"}")
}
```

### Getting Book Details

```kotlin
val openLibraryService = OpenLibraryService()

// Get details for a specific book
val bookId = "OL123456M"
val book = openLibraryService.getBookDetails(bookId)

book?.let {
    println("Title: ${it.title}")
    println("Author: ${it.author ?: "Unknown"}")
    println("Published: ${it.publishYear ?: "Unknown"}")
    println("Cover URL: ${it.coverUrl ?: "No cover available"}")
}
```

## Dependencies

- Retrofit for API requests
- OkHttp for HTTP client
- Gson for JSON parsing

## Permissions

This module requires the Internet permission, which is included in the module's manifest:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```