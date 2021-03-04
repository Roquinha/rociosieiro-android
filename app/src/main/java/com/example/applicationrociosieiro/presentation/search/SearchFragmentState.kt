package com.example.applicationrociosieiro.presentation.search

import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.base.presentation.BaseState
import kotlinx.android.parcel.Parcelize

sealed class SearchFragmentState: BaseState() {

    @Parcelize
    data class Results(var artistList: List<ArtistItemEntity>) : SearchFragmentState()

    @Parcelize
    object EmptyState : SearchFragmentState()

    @Parcelize
    object ErrorConexion : SearchFragmentState()

}