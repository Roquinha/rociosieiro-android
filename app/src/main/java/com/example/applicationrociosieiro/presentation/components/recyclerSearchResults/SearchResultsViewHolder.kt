package com.example.applicationrociosieiro.presentation.components.recyclerSearchResults

import android.view.View
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.presentation.components.baseViewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.result_item_layout.view.*

open class SearchResultsViewHolder (view: View) : BaseViewHolder(view){

    fun bind(
            artistItem: ArtistItemEntity,
            callback: SearchResultsAdapter.OnActionCallback) {

        itemView.tvArtist.text = artistItem.name

        itemView.clArtist.setOnClickListener {
            callback.onArtistSelected(artistItem)
        }

        itemView.ivShare.setOnClickListener {
            callback.onShareSelected(artistItem)
        }
    }
}