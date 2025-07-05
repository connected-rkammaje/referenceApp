package com.tw.openlibrarybooks

import android.content.Context
import com.tw.common.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Android-specific implementation of ResourceProvider that uses Context to access string resources.
 */
class AndroidResourceProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}
