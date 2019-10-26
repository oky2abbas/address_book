package com.oky2abbas.addressbook.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val composite by lazy { CompositeDisposable() }
    private val liveError by lazy { MutableLiveData<String>() }

    fun liveError(): LiveData<String> {
        return liveError
    }

    fun Disposable.addToComposite() {
        composite.add(this)
    }

    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }
}