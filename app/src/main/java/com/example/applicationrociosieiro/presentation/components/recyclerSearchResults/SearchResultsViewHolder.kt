package com.example.applicationrociosieiro.presentation.components.recyclerSearchResults

import android.view.View
import coil.load
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.presentation.components.baseViewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.result_item_layout.view.*

open class SearchResultsViewHolder (view: View) : BaseViewHolder(view){

    fun bind(
            artistItem: ArtistItemEntity,
            callback: SearchResultsAdapter.OnActionCallback) {

        itemView.tvArtist.text = artistItem.name
        if(!artistItem.images.isNullOrEmpty()){
            itemView.ivArtist.load(artistItem.images[0].url?: "")
        }


        itemView.clArtist.setOnClickListener {
            callback.onArtistSelected(artistItem.id ?: "")
        }

        itemView.ivShare.setOnClickListener {
            callback.onShareSelected(artistItem)
        }
    }
}