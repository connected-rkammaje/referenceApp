package com.tw.common

/**
 * Interface for providing string resources.
 * This abstraction allows ViewModels to access string resources without directly
 * depending on Android's Context or Application classes, making them more testable.
 */
interface ResourceProvider {
    /**
     * Gets a string resource by its ID.
     */
    fun getString(resId: Int): String

    /**
     * Gets a formatted string resource by its ID with format arguments.
     */
    fun getString(resId: Int, vararg formatArgs: Any): String
}
