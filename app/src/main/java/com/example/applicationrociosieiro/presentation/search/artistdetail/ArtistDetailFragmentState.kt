package com.example.applicationrociosieiro.presentation.search.artistdetail

import com.example.applicationrociosieiro.common.entities.artists.AlbumItemEntity
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.base.presentation.BaseState
import kotlinx.android.parcel.Parcelize

sealed class ArtistDetailFragmentState: BaseState() {

    @Parcelize
    data class ArtistDetail(var artistDetail: ArtistItemEntity) : ArtistDetailFragmentState()

    @Parcelize
    data class ArtistAlbums(var albumsList: List<AlbumItemEntity> = listOf(), var zoom: Int = 2) : ArtistDetailFragmentState()

    @Parcelize
    object EmptyState : ArtistDetailFragmentState()

    @Parcelize
    object ErrorConexion : ArtistDetailFragmentState()

}