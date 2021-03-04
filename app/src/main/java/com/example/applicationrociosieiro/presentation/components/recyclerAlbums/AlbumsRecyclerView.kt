package com.example.applicationrociosieiro.presentation.components.recyclerAlbums

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationrociosieiro.common.entities.artists.AlbumItemEntity
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.presentation.components.recyclerAlbums.AlbumsAdapter

class AlbumsRecyclerView: RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun init(layoutManager: GridLayoutManager) {
        setHasFixedSize(true)
        adapter = AlbumsAdapter()

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when {
                    (adapter as? AlbumsAdapter)?.getItemViewType(position) == AlbumsAdapter.ONE -> {
                        3
                    }
                    (adapter as? AlbumsAdapter)?.getItemViewType(position) == AlbumsAdapter.TWO -> {
                        1
                    }
                    else -> {
                        1
                    }
                }
            }
        }
    }

    fun update(albums: List<AlbumItemEntity>) {
        (adapter as? AlbumsAdapter)?.submitList(albums)
        (adapter as? AlbumsAdapter)?.notifyDataSetChanged()
    }
}