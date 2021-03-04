package com.example.applicationrociosieiro.data.retrofit
import com.example.applicationrociosieiro.data.authToken.AuthToken
import com.example.applicationrociosieiro.data.authToken.service.AuthTokenService
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class SpotifyInterceptor(private val authTokenService: AuthTokenService) : Interceptor {

    val clientId = "0109fcc33ca147edbe1fe1e28dd05ffe"
    val clientSecret = "907636db6dac4828b041bf641c667c65"

    override fun intercept(chain: Interceptor.Chain): Response {
        requestNewToken()

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${AuthToken.token}")
            .build()

        return chain.proceed(request)
    }

    private fun requestNewToken() {
        val encodedString: String = Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())

        authTokenService.getAuthToken("Basic $encodedString", "client_credentials").execute().body()?.let {
            AuthToken.token = it.accessToken
            AuthToken.type = it.tokenType
        }
    }
}