package com.example.applicationrociosieiro.domain

import com.example.applicationrociosieiro.common.entities.ErrorEntity
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.data.spotify.SpotifyRepository
import com.example.base.domain.BaseUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class Search(
    private val repository: SpotifyRepository
) : BaseUseCase() {

    @ExperimentalCoroutinesApi
    operator fun invoke(
        text: String,
        f: (ErrorEntity) -> Unit,
        s: (List<ArtistItemEntity>) -> Unit
    ) {
        launch {
            repository.getSearchProgramResult(text)
                .catch {
                    f(ErrorEntity.Unknown)
                }.collect {
                    it.fold(
                        { error ->
                            f(error)
                        },
                        { data ->
                            if (data.isNullOrEmpty()) {
                                f(ErrorEntity.EmptyResult)
                            } else {
                                s(data)
                            }
                        }
                    )
                }
        }
    }
}