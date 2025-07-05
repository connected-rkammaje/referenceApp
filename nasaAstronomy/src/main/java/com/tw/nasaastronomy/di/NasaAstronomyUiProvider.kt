package com.tw.nasaastronomy.di

import com.tw.nasaastronomy.NasaFragment
import com.tw.nasaastronomy.NasaAstronomyDetailFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Dagger Hilt module for providing NASA Astronomy UI components from the nasaAstronomy module
 * to other modules that need to display NASA astronomy functionality.
 */
@Module
@InstallIn(FragmentComponent::class)
object NasaAstronomyUiProvider {

    /**
     * Provides NasaFragment for displaying the main NASA astronomy screen.
     * This allows other modules to inject and use the NASA fragment.
     */
    @Provides
    @FragmentScoped
    fun provideNasaFragment(): NasaFragment {
        return NasaFragment.newInstance()
    }

    /**
     * Provides NasaAstronomyDetailFragment for displaying the NASA astronomy detail screen.
     * This allows other modules to inject and use the NASA detail fragment.
     */
    @Provides
    @FragmentScoped
    fun provideNasaAstronomyDetailFragment(): NasaAstronomyDetailFragment {
        return NasaAstronomyDetailFragment.newInstance()
    }
}