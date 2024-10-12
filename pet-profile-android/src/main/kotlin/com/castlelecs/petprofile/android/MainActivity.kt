package com.castlelecs.petprofile.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.castlelecs.petprofile.android.profile.datastores.ActivitiesDataStore
import com.castlelecs.petprofile.android.profile.datastores.PetsDataStore
import com.castlelecs.petprofile.android.profile.viewmodel.PetsProfileViewModel
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.repositories.PetsRepositoryImpl

class MainActivity : ComponentActivity() {

    private val petsRepository = PetsRepositoryImpl(
        petsDataStore = PetsDataStore(),
        activitiesDataStore = ActivitiesDataStore(),
    )
    private val petsInteractor = PetsInteractorImpl(petsRepository)
    private val petsProfileViewModel = PetsProfileViewModel(petsInteractor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                App(petsProfileViewModel)
            }
        }
    }
}
