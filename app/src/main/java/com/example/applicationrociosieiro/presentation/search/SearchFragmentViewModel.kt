package com.example.applicationrociosieiro.presentation.search

import androidx.lifecycle.MutableLiveData
import com.example.applicationrociosieiro.common.entities.ErrorEntity
import com.example.applicationrociosieiro.common.entities.artists.ArtistItemEntity
import com.example.applicationrociosieiro.domain.Search
import com.example.base.presentation.BaseViewModel
import org.koin.core.inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SearchFragmentViewModel: BaseViewModel<SearchFragmentState, SearchFragmentTransition>() {

    private val resultsState = MutableLiveData<SearchFragmentState.Results>()
    private val emptyState = MutableLiveData<SearchFragmentState.EmptyState>()
    private val errorConexionState = MutableLiveData<SearchFragmentState.ErrorConexion>()

    private val search by inject<Search>()

    init {
        @Suppress("UNCHECKED_CAST")
        mStates.add(resultsState as MutableLiveData<SearchFragmentState>)
        mStates.add(emptyState as MutableLiveData<SearchFragmentState>)
        mStates.add(errorConexionState as MutableLiveData<SearchFragmentState>)
    }

    fun search(text: String){
        search(text, ::failureSearch, ::successSearch)
    }

    private fun failureSearch(error: ErrorEntity){
        when(error) {
            is ErrorEntity.EmptyResult -> emptyState.value = SearchFragmentState.EmptyState
            is ErrorEntity.Unknown -> errorConexionState.value = SearchFragmentState.ErrorConexion
            else -> errorConexionState.value = SearchFragmentState.ErrorConexion
        }
    }

    private fun successSearch(artistsList: List<ArtistItemEntity>){
        resultsState.value = SearchFragmentState.Results(artistsList)
    }
}