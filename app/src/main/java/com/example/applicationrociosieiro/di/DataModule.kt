package com.example.applicationrociosieiro.di

import com.example.applicationrociosieiro.data.authToken.service.AuthTokenService
import com.example.applicationrociosieiro.data.retrofit.SpotifyInterceptor
import com.example.applicationrociosieiro.data.spotify.SpotifyRepository
import com.example.applicationrociosieiro.data.spotify.service.SpotifyService
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

@FlowPreview
@kotlinx.coroutines.ExperimentalCoroutinesApi
val dataModule = module {
    single { SpotifyRepository(get()) }

    single { get<Retrofit>(named("auth_retrofit")).create(AuthTokenService::class.java) }
    single(named("auth_retrofit")) {
        Retrofit.Builder()
            .baseUrl("https://accounts.spotify.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .build()
            )
            .build()
    }

    single { get<Retrofit>(named("spotify_retrofit")).create(SpotifyService::class.java) }
    single(named("spotify_retrofit")) {
        Retrofit.Builder()
            .baseUrl("https://api.spotify.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .connectTimeout(30L, TimeUnit.SECONDS)
                    .readTimeout(30L, TimeUnit.SECONDS)
                    .writeTimeout(30L, TimeUnit.SECONDS)
                    .addInterceptor(SpotifyInterceptor(get()))
                    .build())
            .build()
    }

}