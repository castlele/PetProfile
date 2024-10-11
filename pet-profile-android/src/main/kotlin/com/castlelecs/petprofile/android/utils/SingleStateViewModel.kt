package com.castlelecs.petprofile.android.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class SingleStateViewModel<State> : ViewModel() {

    protected abstract val mutableStateFlow: MutableStateFlow<State>

    val stateFlow: StateFlow<State>
        get() = mutableStateFlow

    var state: State
        get() = stateFlow.value
        protected set(value) {
            mutableStateFlow.value = value
        }
}
