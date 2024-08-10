package com.castlelecs.petprofile.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.castlelecs.petprofile.android.screens.Screen
import com.castlelecs.petprofile.android.views.NavigationViewModel
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.repository.PetsRepository

class MainActivity : ComponentActivity() {

    private val petsInteractor = PetsInteractorImpl(PetsRepository(mutableMapOf()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navigationViewModel = NavigationViewModel(
            petsInteractor = petsInteractor,
        )
        navigationViewModel.pushScreen(Screen.PETS_LIST)

        setContent {
            MyApplicationTheme {
                Surface {
                    AppView(navigationViewModel = navigationViewModel)
                }
            }
        }
    }
}
