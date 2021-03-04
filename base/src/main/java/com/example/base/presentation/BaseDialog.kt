package com.example.base.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseDialog<S : BaseState, T : BaseTransition> : AppCompatDialogFragment() {

    protected abstract val layoutRes: Int
    protected abstract val viewModel: BaseViewModel<S, T>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.apply { viewModel.restoreInstance(this) }
        initViews()
        initObservers()
    }

    protected abstract fun initViews()

    private fun initObservers() {
        viewModel.getStates().forEach { states ->
            states.observe(viewLifecycleOwner, Observer {
                manageState(it)
            })
        }
        viewModel.getTransition().observe(viewLifecycleOwner, Observer {
            manageTransition(it)
        })
    }

    protected abstract fun manageState(state: S)

    protected abstract fun manageTransition(transition: T)


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveInstance(outState)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        parentFragment?.apply { onAttach(this) }
    }

    open fun onAttach(fragment: Fragment) {
        // callback - do nothing
    }

}
