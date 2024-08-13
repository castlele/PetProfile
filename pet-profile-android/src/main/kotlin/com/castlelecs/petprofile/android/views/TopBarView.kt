package com.castlelecs.petprofile.android.views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import com.castlelecs.petprofile.android.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(
    currentScreen: Screen,
    isNavigationBackEnabled: Boolean,
    onBackButtonClicked: () -> Unit,
    onEditButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = currentScreen.topBarTitle))
        },
        navigationIcon = {
            if (isNavigationBackEnabled) {
                IconButton(onClick = onBackButtonClicked) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            when (currentScreen) {
                Screen.PROFILE -> {
                    IconButton(onClick = onEditButtonClicked) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                    }
                }
                else -> {}
            }
        },
        modifier = modifier,
    )
}
