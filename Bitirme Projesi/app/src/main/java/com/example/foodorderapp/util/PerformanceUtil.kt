package com.example.foodorderapp.util

import android.content.Context
import android.os.Build
import android.os.StrictMode
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PerformanceUtil {
    private const val TAG = "PerformanceUtil"
    private var startTime: Long = 0

    fun enableStrictMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .detectCustomSlowCalls()
                    .penaltyLog()
                    .build()
            )

            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedClosableObjects()
                    .detectActivityLeaks()
                    .detectLeakedRegistrationObjects()
                    .penaltyLog()
                    .build()
            )
        }
    }

    fun startMeasuring() {
        startTime = System.nanoTime()
    }

    fun endMeasuring(operationName: String) {
        val endTime = System.nanoTime()
        val duration = (endTime - startTime) / 1_000_000 // Convert to milliseconds
        Log.d(TAG, "$operationName took $duration ms")
    }

    suspend fun <T> measureOperation(
        operationName: String,
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        operation: suspend () -> T
    ): T {
        startMeasuring()
        return withContext(dispatcher) {
            try {
                operation().also {
                    endMeasuring(operationName)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error in $operationName: ${e.message}")
                throw e
            }
        }
    }
}

@Composable
fun RememberLifecycleEvent(onEvent: (Lifecycle.Event) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val observer = remember(lifecycleOwner) {
        LifecycleEventObserver { _, event ->
            onEvent(event)
        }
    }

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(observer)
    }
}
