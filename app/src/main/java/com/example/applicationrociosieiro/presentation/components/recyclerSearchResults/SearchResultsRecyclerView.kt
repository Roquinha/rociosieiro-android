package com.example.applicationrociosieiro.presentation.components.recyclerSearchResults

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity

class SearchResultsRecyclerView: RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        setHasFixedSize(true)
        adapter = SearchResultsAdapter()
    }

    fun update(artistList: List<ArtistItemEntity>, fragment: SearchResultsAdapter.OnActionCallback) {
        (adapter as? SearchResultsAdapter)?.submitList(artistList)
        (adapter as? SearchResultsAdapter)?.apply {
            callback = fragment
        }
    }
}