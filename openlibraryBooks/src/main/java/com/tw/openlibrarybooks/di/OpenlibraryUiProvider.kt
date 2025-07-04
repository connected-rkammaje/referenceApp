package com.tw.openlibrarybooks.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Dagger Hilt module for providing UI components from the openlibrarybooks module
 * to other modules that need to display book search functionality.
 *
 * Note: This module is currently empty but maintained for future UI component providers.
 * The BookSearchScreen is now used directly without injection.
 */
@Module
@InstallIn(FragmentComponent::class)
object OpenlibraryUiProvider
