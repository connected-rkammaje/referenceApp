package com.tw.networking;

/**
 * Service class for interacting with the Open Library API.
 * This class provides methods to search for books and retrieve book details.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\r\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/tw/networking/OpenLibraryService;", "", "api", "Lcom/tw/networking/OpenLibraryApi;", "(Lcom/tw/networking/OpenLibraryApi;)V", "getBookDetails", "Lcom/tw/common/RequestResult;", "Lcom/tw/networking/Book;", "bookId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchBooks", "Lcom/tw/networking/data/SearchResponse;", "query", "networking_debug"})
public final class OpenLibraryService {
    @org.jetbrains.annotations.NotNull()
    private final com.tw.networking.OpenLibraryApi api = null;
    
    @javax.inject.Inject()
    public OpenLibraryService(@org.jetbrains.annotations.NotNull()
    com.tw.networking.OpenLibraryApi api) {
        super();
    }
    
    /**
     * Search for books by query.
     * @param query The search query
     * @return RequestResult containing either loading, success with search response, or error
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchBooks(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tw.common.RequestResult<com.tw.networking.data.SearchResponse>> $completion) {
        return null;
    }
    
    /**
     * Get details for a specific book by ID.
     * @param bookId The Open Library book ID
     * @return RequestResult containing either loading, success with book details, or error
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBookDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tw.common.RequestResult<com.tw.networking.Book>> $completion) {
        return null;
    }
}