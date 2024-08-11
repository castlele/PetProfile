package com.castlelecs.petprofile.android.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileView(
    state: ProfileViewState,
    modifier: Modifier = Modifier,
) {

    Text(
        "Hello, world!",
        modifier = modifier
    )
}
