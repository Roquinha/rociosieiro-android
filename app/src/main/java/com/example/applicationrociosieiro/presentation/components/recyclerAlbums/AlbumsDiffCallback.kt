package com.example.applicationrociosieiro.presentation.components.recyclerAlbums

import androidx.recyclerview.widget.DiffUtil
import com.example.applicationrociosieiro.common.entities.artists.AlbumItemEntity

class AlbumsDiffCallback : DiffUtil.ItemCallback<AlbumItemEntity>() {

    override fun areItemsTheSame(oldAlbumItem: AlbumItemEntity, newAlbumItem: AlbumItemEntity) =
        oldAlbumItem == newAlbumItem

    override fun areContentsTheSame(oldAlbumItem: AlbumItemEntity, newAlbumItem: AlbumItemEntity) =
        oldAlbumItem == newAlbumItem
}