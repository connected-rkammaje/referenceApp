package com.tw.networking.di

import com.tw.networking.OpenLibraryApi
import com.tw.networking.OpenLibraryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkingModule {

    private const val BASE_URL = "https://openlibrary.org/"

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOpenLibraryApi(retrofit: Retrofit): OpenLibraryApi {
        return retrofit.create(OpenLibraryApi::class.java)
    }

    @Provides
    fun provideOpenLibraryService(api: OpenLibraryApi): OpenLibraryService {
        return OpenLibraryService(api)
    }
}
