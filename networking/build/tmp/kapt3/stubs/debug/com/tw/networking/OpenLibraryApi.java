package com.tw.networking;

/**
 * Retrofit interface for the Open Library API.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\n"}, d2 = {"Lcom/tw/networking/OpenLibraryApi;", "", "getBookDetails", "Lcom/tw/networking/data/BookDetails;", "bookId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchBooks", "Lcom/tw/networking/data/SearchResponse;", "query", "networking_debug"})
public abstract interface OpenLibraryApi {
    
    /**
     * Search for books by query.
     * @param query The search query
     * @return The search response
     */
    @retrofit2.http.GET(value = "search.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchBooks(@retrofit2.http.Query(value = "q")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tw.networking.data.SearchResponse> $completion);
    
    /**
     * Get details for a specific book by ID.
     * @param bookId The Open Library book ID
     * @return The book details
     */
    @retrofit2.http.GET(value = "books/{bookId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBookDetails(@retrofit2.http.Path(value = "bookId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tw.networking.data.BookDetails> $completion);
}