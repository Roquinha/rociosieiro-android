package com.example.applicationrociosieiro.data.spotify.service

import com.example.applicationrociosieiro.common.entities.artists.AlbumEntity
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.common.entities.artists.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    @GET("/v1/search")
    suspend fun search(@Query("q") name: String,
                       @Query("type") type: String) : Response<SearchResult>

    @GET("/v1/artists/{id}")
    suspend fun getArtist(@Path("id") id: String): Response<ArtistItemEntity>

    @GET("/v1/artists/{id}/albums")
    suspend fun getAlbumsArtist(@Path("id") id: String) : Response<AlbumEntity>

}