package com.example.applicationrociosieiro.presentation.components.recyclerSearchResults

import androidx.recyclerview.widget.DiffUtil
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity

class SearchResultsDiffCallback : DiffUtil.ItemCallback<ArtistItemEntity>() {

    override fun areItemsTheSame(oldArtistItem: ArtistItemEntity, newArtistItem: ArtistItemEntity) =
        oldArtistItem == newArtistItem

    override fun areContentsTheSame(oldArtistItem: ArtistItemEntity, newArtistItem: ArtistItemEntity) =
        oldArtistItem == newArtistItem
}