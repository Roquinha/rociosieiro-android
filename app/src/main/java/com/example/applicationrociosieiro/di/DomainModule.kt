package com.example.applicationrociosieiro.di

import com.example.applicationrociosieiro.domain.GetAlbums
import com.example.applicationrociosieiro.domain.GetArtist
import com.example.applicationrociosieiro.domain.Search
import org.koin.dsl.module

@kotlinx.coroutines.ExperimentalCoroutinesApi
val domainModule = module {

   factory { Search(get()) }
   factory { GetArtist(get()) }
   factory { GetAlbums(get()) }

}