package com.castlelecs.petprofile.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.castlelecs.petprofile.android.helpers.CreateComposable
import com.castlelecs.petprofile.android.views.NavigationViewModel
import com.castlelecs.petprofile.android.views.TopBarView
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.repository.PetsRepository

@Composable
fun AppView(
    navigationViewModel: NavigationViewModel,
) {
    val state = navigationViewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            TopBarView(
                navigationViewModel = navigationViewModel,
            )
        },
        floatingActionButton = {
            // TODO: Move to separate view
            FloatingActionButton(
                onClick = navigationViewModel::onFloatingButtonClicked
            ) {
                if (state.floatingButtonIcon != null) {
                    Icon(state.floatingButtonIcon, contentDescription = null)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            navigationViewModel.CreateComposable()
        }
    }
}

@Preview
@Composable
fun AppViewPreview() {
    AppView(NavigationViewModel(PetsInteractorImpl(PetsRepository(mutableMapOf()))))
}