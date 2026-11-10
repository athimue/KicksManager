package com.athimue.kicksmanager

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.crossfade
import dagger.hilt.android.HiltAndroidApp

/**
 * Kicks Manager to use Hilt.
 * Also configures Coil so every sneaker picture fades in smoothly.
 */
@HiltAndroidApp
class KicksManagerApplication :
    Application(),
    SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader =
        ImageLoader.Builder(context)
            .crossfade(true)
            .build()
}
