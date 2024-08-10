package com.castlelecs.petprofile.android.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector
import com.castlelecs.petprofile.android.R
import com.castlelecs.petprofile.android.helpers.BaseViewModel
import com.castlelecs.petprofile.android.screens.Screen
import com.castlelecs.petprofile.interactors.PetsInteractor

class NavigationViewModel(
    internal val petsInteractor: PetsInteractor,
) : BaseViewModel<NavigationViewModel.NavigationState>(NavigationState()) {

    data class NavigationState(
        val titleId: Int = 0,
        val isBackButtonHidden: Boolean = true,
        val currentScreen: Screen = Screen.PETS_LIST,
        val floatingButtonIcon: ImageVector? = null,
    )

    fun onFloatingButtonClicked() {
        when (state.value.currentScreen) {
            Screen.PETS_LIST -> pushScreen(Screen.CREATE_PROFILE)
            Screen.CREATE_PROFILE -> TODO()
        }
    }

    fun pushScreen(screen: Screen) {
        mutableStateFlow.value = when (screen) {
            Screen.PETS_LIST -> {
                mutableStateFlow.value.copy(
                    titleId = R.string.app_name,
                    isBackButtonHidden = true,
                    currentScreen = screen,
                    floatingButtonIcon = Icons.Filled.Add,
                )
            }
            Screen.CREATE_PROFILE -> TODO()
        }
    }
}
