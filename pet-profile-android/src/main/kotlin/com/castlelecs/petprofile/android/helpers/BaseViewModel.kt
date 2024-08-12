package com.castlelecs.petprofile.android.helpers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.castlelecs.utils.logger.compositeLogger

open class BaseViewModel<T>(defaultState: T) : ViewModel() {
    val state: StateFlow<T>
        get() = mutableStateFlow.asStateFlow()

    val stateValue: T
        get() = state.value

    protected val mutableStateFlow = MutableStateFlow(defaultState)
    protected val logger = compositeLogger(
        system = this::class.simpleName ?: TAG
    )

    companion object {
        private const val TAG = "BaseViewModel"
    }
}
