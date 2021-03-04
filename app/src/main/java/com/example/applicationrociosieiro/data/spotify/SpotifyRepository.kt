package com.example.applicationrociosieiro.data.spotify

import com.example.applicationrociosieiro.common.entities.ErrorEntity
import com.example.applicationrociosieiro.data.functions.handleRetrofitResponseFunc
import com.example.applicationrociosieiro.data.spotify.service.SpotifyService
import com.example.base.common.Either
import com.example.base.data.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.flow

class SpotifyRepository(private val spotifyService: SpotifyService): BaseRepository() {

    @ExperimentalCoroutinesApi
    fun getSearchProgramResult(text: String) = flow {
        handleRetrofitResponseFunc(spotifyService.search(text, "artist")).fold({
            emit(Either.Error(it))
        }) {
            it.artists?.let { artistList ->
                emit(Either.Value(artistList.artistItems))
            } ?: emit(Either.Error(ErrorEntity.EmptyResult))
        }
    }.catch {
        emit(Either.Error(ErrorEntity.Unknown))
    }.flowOn(Dispatchers.IO)


    @ExperimentalCoroutinesApi
    fun getArtist(id: String) = flow {
        handleRetrofitResponseFunc(spotifyService.getArtist(id)).fold({
            emit(Either.Error(it))
        }) {
            emit(Either.Value(it))
        }
    }.catch {
        emit(Either.Error(ErrorEntity.Unknown))
    }.flowOn(Dispatchers.IO)


    @ExperimentalCoroutinesApi
    fun getAlbumsArtist(id: String) = flow {
        handleRetrofitResponseFunc(spotifyService.getAlbumsArtist(id)).fold({
            emit(Either.Error(it))
        }) {
            it.albumItems?.let { albumList ->
                emit(Either.Value(albumList))
            } ?: emit(Either.Error(ErrorEntity.EmptyResult))
        }
    }.catch {
        emit(Either.Error(ErrorEntity.Unknown))
    }.flowOn(Dispatchers.IO)
}