package com.example.applicationrociosieiro.di

import com.example.applicationrociosieiro.presentation.search.SearchActivityViewModel
import com.example.applicationrociosieiro.presentation.search.SearchFragmentViewModel
import com.example.applicationrociosieiro.presentation.search.artistdetail.ArtistDetailFragmentViewModel
import com.example.applicationrociosieiro.presentation.search.artistdetail.ArtistDetailViewEntry
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@kotlinx.coroutines.ExperimentalCoroutinesApi
val presentationModule = module {
    viewModel { SearchActivityViewModel() }
    viewModel { SearchFragmentViewModel() }

    viewModel { (entry: ArtistDetailViewEntry) ->
        ArtistDetailFragmentViewModel(
            entry
        )
    }
}