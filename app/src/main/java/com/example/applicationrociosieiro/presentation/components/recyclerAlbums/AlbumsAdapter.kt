package com.example.applicationrociosieiro.presentation.components.recyclerAlbums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.applicationrociosieiro.R
import com.example.applicationrociosieiro.common.entities.artists.AlbumItemEntity
import com.example.applicationrociosieiro.presentation.components.baseViewHolder.BaseViewHolder

class AlbumsAdapter: ListAdapter<AlbumItemEntity, BaseViewHolder>(
    AlbumsDiffCallback()
) {

    companion object{
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return AlbumsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder as AlbumsViewHolder
        holder.bind(getItem(position))
    }


    override fun getItemViewType(position: Int): Int {
        return when(getItem(position).zoom){
            1 -> ONE
            2 -> TWO
            else -> THREE
        }
    }
}