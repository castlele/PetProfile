package com.castlelecs.petprofile.android.screens.petlist

import androidx.compose.runtime.Composable
import com.castlelecs.petprofile.android.views.NavigationViewModel

object PetListScreenAssembler {

    @Composable
    fun Assemble(navigationViewModel: NavigationViewModel) {
        val viewModel = PetsListViewModel(navigationViewModel.petsInteractor)

        PetsListScreen(viewModel)
    }
}
