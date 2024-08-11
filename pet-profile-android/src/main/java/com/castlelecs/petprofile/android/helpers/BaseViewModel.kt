package com.castlelecs.petprofile.android.helpers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<T>(defaultState: T) : ViewModel() {
    val state: StateFlow<T>
        get() = mutableStateFlow.asStateFlow()

    val stateValue: T
        get() = state.value

    protected val mutableStateFlow = MutableStateFlow(defaultState)
}
