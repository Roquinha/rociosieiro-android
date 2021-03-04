package com.example.applicationrociosieiro

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.applicationrociosieiro.di.dataModule
import com.example.applicationrociosieiro.di.domainModule
import com.example.applicationrociosieiro.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("EXPERIMENTAL_API_USAGE")
class StartApplication : Application(),
    Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        initDi()
        initLifecycle()
/*
        Coil.setImageLoader {
            ImageLoader.Builder(context = baseContext)
                .crossfade(true)
                .allowHardware(false)
                .okHttpClient {
                    val cacheDirectory = File(filesDir, "image_cache").apply { mkdirs() }
                    val cache = Cache(cacheDirectory, Long.MAX_VALUE)
                    val cacheControlInterceptor =
                        ResponseHeaderInterceptor("Cache-Control", "max-age=31536000,public")
                    OkHttpClient.Builder()
                        .cache(cache)
                        .addNetworkInterceptor(cacheControlInterceptor)
                        .build()
                }
                .build()
        }*/
    }

    private fun initDi() {
        startKoin {
            androidContext(this@StartApplication)

            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule
                )
            )
        }
    }

    private fun initLifecycle() {
        registerActivityLifecycleCallbacks(this)
    }


    override fun onActivityStarted(activity: Activity) {
        // don't implement this event
    }

    override fun onActivityDestroyed(p0: Activity) {
        // don't implement this event
    }

    override fun onActivityResumed(activity: Activity) {
        // don't implement this event
    }

    override fun onActivityPaused(activity: Activity) {
        // don't implement this event
    }

    override fun onActivityStopped(activity: Activity) {
        // don't implement this event
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        // don't implement this event
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // don't implement this event
    }


}
