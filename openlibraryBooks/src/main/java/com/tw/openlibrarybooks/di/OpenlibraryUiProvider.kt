package com.tw.openlibrarybooks.di

import com.tw.common.ResourceProvider
import com.tw.openlibrarybooks.AndroidResourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Dagger Hilt module for providing UI components from the openlibrarybooks module
 * to other modules that need to display book search functionality.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class OpenlibraryUiProvider {

    @Binds
    abstract fun bindResourceProvider(
        androidResourceProvider: AndroidResourceProvider
    ): ResourceProvider
}
