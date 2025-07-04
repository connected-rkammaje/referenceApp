package com.tw.openlibrarybooks.di

import com.tw.networking.OpenLibraryService
import com.tw.openlibrarybooks.OpenlibraryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object OpenlibraryApiProvider {

    @Provides
    fun provideOpenlibraryRepository(openLibraryService: OpenLibraryService): OpenlibraryRepository {
        return OpenlibraryRepository(openLibraryService)
    }
}
