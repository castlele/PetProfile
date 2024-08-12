package com.castlelecs.petprofile.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.castlelecs.petprofile.android.screens.PetsViewModel
import com.castlelecs.petprofile.android.interactors.PetsInteractorDecorator
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.repository.PetsRepository

class MainActivity : ComponentActivity() {

    private val petsInteractor = PetsInteractorImpl(PetsRepository(mutableMapOf()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface {
                    AppView(
                        viewModel = PetsViewModel(
                            PetsInteractorDecorator(petsInteractor)
                        )
                    )
                }
            }
        }
    }
}
