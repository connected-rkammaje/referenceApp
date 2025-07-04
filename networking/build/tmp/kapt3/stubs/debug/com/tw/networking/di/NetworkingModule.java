package com.tw.networking.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/tw/networking/di/NetworkingModule;", "", "()V", "BASE_URL", "", "provideHttpLoggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "loggingInterceptor", "provideOpenLibraryApi", "Lcom/tw/networking/OpenLibraryApi;", "retrofit", "Lretrofit2/Retrofit;", "provideOpenLibraryService", "Lcom/tw/networking/OpenLibraryService;", "api", "provideRetrofit", "client", "networking_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.android.components.ViewModelComponent.class})
public final class NetworkingModule {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://openlibrary.org/";
    @org.jetbrains.annotations.NotNull()
    public static final com.tw.networking.di.NetworkingModule INSTANCE = null;
    
    private NetworkingModule() {
        super();
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.logging.HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideOkHttpClient(@org.jetbrains.annotations.NotNull()
    okhttp3.logging.HttpLoggingInterceptor loggingInterceptor) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideRetrofit(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient client) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.tw.networking.OpenLibraryApi provideOpenLibraryApi(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.tw.networking.OpenLibraryService provideOpenLibraryService(@org.jetbrains.annotations.NotNull()
    com.tw.networking.OpenLibraryApi api) {
        return null;
    }
}