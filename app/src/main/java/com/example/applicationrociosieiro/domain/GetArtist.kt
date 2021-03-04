package com.example.applicationrociosieiro.domain

import com.example.applicationrociosieiro.common.entities.ErrorEntity
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.data.spotify.SpotifyRepository
import com.example.base.domain.BaseUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class GetArtist(private val repository: SpotifyRepository)  : BaseUseCase() {

    @ExperimentalCoroutinesApi
    operator fun invoke(
        id: String,
        f: (ErrorEntity) -> Unit,
        s: (ArtistItemEntity) -> Unit
    ) {
        if(id.isNotEmpty()){
            launch {
                repository.getArtist(id)
                    .catch {
                        f(ErrorEntity.Unknown)
                    }.collect {
                        it.fold(
                            { error ->
                                f(error)
                            },
                            { data ->
                                s(data)
                            }
                        )
                    }
            }
        } else {
            f(ErrorEntity.EmptyResult)
        }
    }
}