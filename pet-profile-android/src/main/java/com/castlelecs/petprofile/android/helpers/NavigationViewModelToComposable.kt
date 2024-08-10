package com.castlelecs.petprofile.android.helpers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.castlelecs.petprofile.android.screens.Screen
import com.castlelecs.petprofile.android.screens.petlist.PetListScreenAssembler
import com.castlelecs.petprofile.android.views.NavigationViewModel

@Composable
fun NavigationViewModel.CreateComposable() {
    when (state.collectAsState().value.currentScreen) {
        Screen.PETS_LIST -> PetListScreenAssembler.Assemble(this)
        Screen.CREATE_PROFILE -> TODO()
    }
}