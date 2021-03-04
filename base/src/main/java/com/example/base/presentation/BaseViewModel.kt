package com.example.base.presentation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.common.SingleLiveEvent
import org.koin.core.KoinComponent

abstract class BaseViewModel<S : BaseState, T : BaseTransition> : ViewModel(), KoinComponent {

    protected val mStates = mutableListOf<MutableLiveData<S>>()
    protected val mTransition = SingleLiveEvent<T>()

    fun getStates(): List<LiveData<S>> = mStates
    fun getTransition(): LiveData<T> = mTransition


    fun restoreInstance(instance: Bundle) {
        val size = instance.getInt("size")
        for (i in 0 until size) {
            instance.getParcelable<S>("state$i")?.apply { mStates[i].value = this }
        }
    }

    fun saveInstance(instance: Bundle) {
        instance.putInt("size", mStates.size)
        mStates.forEachIndexed { i, state ->
            instance.putParcelable("state$i", state.value)
        }
    }
}
