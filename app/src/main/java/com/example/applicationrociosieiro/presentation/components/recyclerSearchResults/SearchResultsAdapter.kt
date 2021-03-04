package com.example.applicationrociosieiro.presentation.components.recyclerSearchResults

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.applicationrociosieiro.R
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.presentation.components.baseViewHolder.BaseViewHolder

class SearchResultsAdapter: ListAdapter<ArtistItemEntity, BaseViewHolder>(
    SearchResultsDiffCallback()
) {
    var callback: OnActionCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return SearchResultsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.result_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder as SearchResultsViewHolder
        callback?.let {
            holder.bind(getItem(position), it)
        }
    }

    interface OnActionCallback {
        fun onShareSelected(bulletin: ArtistItemEntity)
        fun onArtistSelected(bulletin: ArtistItemEntity)
    }
}