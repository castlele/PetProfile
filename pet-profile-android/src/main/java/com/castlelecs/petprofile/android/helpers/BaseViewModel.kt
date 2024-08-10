package com.castlelecs.petprofile.android.helpers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel<T>(defaultState: T) : ViewModel() {
    val state: StateFlow<T>
        get() = mutableStateFlow

    protected val mutableStateFlow = MutableStateFlow(defaultState)
}
