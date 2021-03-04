package com.example.applicationrociosieiro.presentation.components.recyclerAlbums

import android.view.View
import coil.load
import com.example.applicationrociosieiro.common.entities.artists.AlbumItemEntity
import com.example.applicationrociosieiro.presentation.components.baseViewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.album_item_layout.view.*

open class AlbumsViewHolder (view: View) : BaseViewHolder(view){

    fun bind(albumItem: AlbumItemEntity) {
        itemView.tvAlbum.text = albumItem.name
        if(!albumItem.images.isNullOrEmpty()){
            itemView.ivAlbum.load(albumItem.images[0].url?: "")
        }
    }
}