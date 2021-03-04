package com.example.applicationrociosieiro.domain

import com.example.applicationrociosieiro.common.entities.ErrorEntity
import com.example.applicationrociosieiro.common.entities.artists.AlbumItemEntity
import com.example.applicationrociosieiro.data.spotify.SpotifyRepository
import com.example.base.domain.BaseUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class GetAlbums(private val repository: SpotifyRepository)  : BaseUseCase() {

    @ExperimentalCoroutinesApi
    operator fun invoke(
        id: String,
        f: (ErrorEntity) -> Unit,
        s: (List<AlbumItemEntity>) -> Unit
    ) {
        if(id.isNotEmpty()){
            launch {
                repository.getAlbumsArtist(id)
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
            f(ErrorEntity.EmptyAlbums)
        }
    }
}