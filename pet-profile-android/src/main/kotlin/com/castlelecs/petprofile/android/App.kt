package com.castlelecs.petprofile.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.castlelecs.petprofile.android.profile.view.Profile
import com.castlelecs.petprofile.android.profile.viewmodel.PetsProfileViewModel

@Composable
fun App(
    petsProfileViewModel: PetsProfileViewModel,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Profile(vm = petsProfileViewModel, modifier = Modifier.fillMaxSize())
    }
}
