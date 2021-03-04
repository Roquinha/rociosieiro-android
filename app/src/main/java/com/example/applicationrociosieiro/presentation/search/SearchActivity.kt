package com.example.applicationrociosieiro.presentation.search

import com.example.applicationrociosieiro.R
import com.example.base.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<SearchActivityState, SearchActivityTransition>() {

    override val layoutRes: Int = R.layout.activity_main
    override val viewModel by viewModel<SearchActivityViewModel>()


    override fun initViews() {

    }

    override fun manageState(state: SearchActivityState) {

    }

    override fun manageTransition(transition: SearchActivityTransition) {

    }

}


