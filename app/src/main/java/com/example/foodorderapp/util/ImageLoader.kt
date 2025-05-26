package com.example.foodorderapp.util

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import java.io.File

object ImageLoaderUtil {
    private var imageLoader: ImageLoader? = null

    fun getImageLoader(context: Context): ImageLoader {
        if (imageLoader == null) {
            imageLoader = ImageLoader.Builder(context)
                .memoryCache {
                    MemoryCache.Builder(context)
                        .maxSizePercent(0.25) // Use 25% of app memory for image cache
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(File(context.cacheDir, "image_cache"))
                        .maxSizePercent(0.02) // Use 2% of disk space
                        .build()
                }
                .crossfade(true)
                .build()
        }
        return imageLoader!!
    }

    @Composable
    fun OptimizedAsyncImage(
        url: String,
        contentDescription: String?,
        modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
        isCircular: Boolean = false,
        cornerRadius: Float = 0f
    ) {
        val context = LocalContext.current
        val imageLoader = getImageLoader(context)

        val request = ImageRequest.Builder(context)
            .data(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .scale(Scale.FILL)
            .crossfade(true)
            .apply {
                when {
                    isCircular -> transformations(CircleCropTransformation())
                    cornerRadius > 0 -> transformations(RoundedCornersTransformation(cornerRadius))
                }
            }
            .build()

        AsyncImage(
            model = request,
            contentDescription = contentDescription,
            modifier = modifier,
            imageLoader = imageLoader
        )
    }

    fun clearImageCache(context: Context) {
        imageLoader?.let { loader ->
            loader.memoryCache?.clear()
            loader.diskCache?.clear()
        }
    }
}
