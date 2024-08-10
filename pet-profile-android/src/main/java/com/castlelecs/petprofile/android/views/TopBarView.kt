package com.castlelecs.petprofile.android.views

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TopBarView(
    navigationViewModel: NavigationViewModel,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val state = navigationViewModel.state.collectAsState().value

    TopBarView(
        title = stringResource(id = state.titleId),
        isNavigationIconHidden = state.isBackButtonHidden,
        modifier = modifier,
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarView(
    title: String,
    isNavigationIconHidden: Boolean,
    modifier: Modifier,
    actions: @Composable RowScope.() -> Unit,
) {
    TopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {

        },
        actions = actions,
        modifier = modifier,
    )
}