package com.example.applicationrociosieiro.presentation.search.artistdetail

import androidx.lifecycle.MutableLiveData
import com.example.applicationrociosieiro.common.entities.ErrorEntity
import com.example.applicationrociosieiro.common.entities.artists.AlbumItemEntity
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.domain.GetAlbums
import com.example.applicationrociosieiro.domain.GetArtist
import com.example.base.presentation.BaseViewModel
import org.koin.core.inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ArtistDetailFragmentViewModel(private val entry: ArtistDetailViewEntry): BaseViewModel<ArtistDetailFragmentState, ArtistDetailFragmentTransition>() {

    private val detailState = MutableLiveData<ArtistDetailFragmentState.ArtistDetail>()
    private val albumsState = MutableLiveData<ArtistDetailFragmentState.ArtistAlbums>()
    private val emptyState = MutableLiveData<ArtistDetailFragmentState.EmptyState>()
    private val errorConexionState = MutableLiveData<ArtistDetailFragmentState.ErrorConexion>()

    private val getArtistDetail by inject<GetArtist>()
    private val getAlbums by inject<GetAlbums>()

    private val state by lazy {
        albumsState.value ?: ArtistDetailFragmentState.ArtistAlbums()
    }

    init {
        @Suppress("UNCHECKED_CAST")
        mStates.add(detailState as MutableLiveData<ArtistDetailFragmentState>)
        mStates.add(emptyState as MutableLiveData<ArtistDetailFragmentState>)
        mStates.add(errorConexionState as MutableLiveData<ArtistDetailFragmentState>)
        mStates.add(albumsState as MutableLiveData<ArtistDetailFragmentState>)

        getArtistDetail.invoke((entry as ArtistDetailViewEntry.ArtistEntry).id, ::failureDetail, ::successDetail)
        getAlbums.invoke((entry as ArtistDetailViewEntry.ArtistEntry).id, ::failureAlbums, ::successAlbums)
    }


    private fun failureDetail(error: ErrorEntity){
        when(error) {
            is ErrorEntity.EmptyResult -> emptyState.value = ArtistDetailFragmentState.EmptyState
            is ErrorEntity.Unknown -> errorConexionState.value = ArtistDetailFragmentState.ErrorConexion
            else -> errorConexionState.value = ArtistDetailFragmentState.ErrorConexion
        }
    }

    private fun successDetail(artist: ArtistItemEntity){
        detailState.value = ArtistDetailFragmentState.ArtistDetail(artist)
    }


    private fun failureAlbums(error: ErrorEntity){
        when(error) {
            is ErrorEntity.EmptyResult -> emptyState.value = ArtistDetailFragmentState.EmptyState
            is ErrorEntity.Unknown -> errorConexionState.value = ArtistDetailFragmentState.ErrorConexion
            else -> errorConexionState.value = ArtistDetailFragmentState.ErrorConexion
        }
    }

    private fun successAlbums(albums: List<AlbumItemEntity>){
        albumsState.value = ArtistDetailFragmentState.ArtistAlbums(albums)
    }


    fun lessZoom() {
        if(state.zoom < 3){
        state.zoom = state.zoom + 1
        for(album in state.albumsList){
            album.zoom = state.zoom
        }
        albumsState.value = state
        }
    }

    fun moreZoom() {
        if(state.zoom > 1){
            state.zoom = state.zoom - 1
            for(album in state.albumsList){
                album.zoom = state.zoom
            }
            albumsState.value = state
        }
    }
}