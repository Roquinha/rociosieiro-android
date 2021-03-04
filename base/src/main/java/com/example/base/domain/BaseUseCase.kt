package com.example.base.domain

import android.util.Log
import com.example.base.BuildConfig
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseUseCase : KoinComponent {
    private val exceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            if (BuildConfig.DEBUG) {
                Log.d(BaseUseCase::class.java.simpleName, "Caught $exception")
            }
        }

    private var scope : CoroutineScope = getScope()

    protected val isActive: Boolean
        get() = scope.isActive

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        if (!scope.isActive) {
            scope = getScope()
        }
        return scope.launch(context, start, block)
    }

    private fun getScope() =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

    fun cancel(cause: CancellationException? = null) =
        scope.cancel(cause)

}
