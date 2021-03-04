package com.example.applicationrociosieiro.data.authToken.service

import com.example.applicationrociosieiro.common.entities.TokenEntity
import retrofit2.Call
import retrofit2.http.*


interface AuthTokenService {

    @FormUrlEncoded
    @POST("token")
    fun getAuthToken(
        @Header("Authorization") auth: String?,
        @Field("grant_type") body: String
    ): Call<TokenEntity>
}
