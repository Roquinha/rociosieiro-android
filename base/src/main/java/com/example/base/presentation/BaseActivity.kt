package com.example.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<S : BaseState, T : BaseTransition> : AppCompatActivity() {

    companion object {
        private const val PERMISSIONS_TAG = "permissions"
    }


    protected abstract val layoutRes: Int
    protected abstract val viewModel: BaseViewModel<S, T>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
//        savedInstanceState?.apply { viewModel.restoreInstance(this) }
        initViews()
        initObservers()
    }

    abstract fun initViews()

    private fun initObservers() {
        viewModel.getStates().forEach { states ->
            states.observe(this, Observer {
                manageState(it)
            })
        }
        viewModel.getTransition().observe(this, Observer {
            manageTransition(it)
        })
    }

    abstract fun manageState(state: S)

    abstract fun manageTransition(transition: T)

}
