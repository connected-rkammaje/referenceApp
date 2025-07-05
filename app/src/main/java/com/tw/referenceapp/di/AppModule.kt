package com.tw.referenceapp.di

import com.tw.nasaastronomy.di.NasaAstronomyUiProvider
import com.tw.openlibrarybooks.di.OpenlibraryUiProvider
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Main application DI super module that coordinates and provides access to sub-modules.
 * This module acts as a central point for importing and injecting components from various
 * feature modules into the main application.
 *
 * This super module includes:
 * - OpenlibraryUiProvider to make openlibrarybooks UI components available for injection
 * - NasaAstronomyUiProvider to make NASA astronomy UI components available for injection
 */
@Module(includes = [OpenlibraryUiProvider::class, NasaAstronomyUiProvider::class])
@InstallIn(FragmentComponent::class)
object AppModule
