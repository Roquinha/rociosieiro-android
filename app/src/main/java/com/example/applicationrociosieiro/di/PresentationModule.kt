package com.example.applicationrociosieiro.di

import com.example.applicationrociosieiro.presentation.search.SearchActivityViewModel
import com.example.applicationrociosieiro.presentation.search.SearchFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@kotlinx.coroutines.ExperimentalCoroutinesApi
val presentationModule = module {
    viewModel { SearchActivityViewModel() }
    viewModel { SearchFragmentViewModel() }
}