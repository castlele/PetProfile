package com.castlelecs.petprofile.android.screens

import com.castlelecs.petprofile.android.helpers.BaseViewModel
import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.models.Pet

class PetsViewModel(
    private val petsInteractor: PetsInteractor
) : BaseViewModel<PetsViewModel.State>(State()) {

    data class State(
        val isFloatingButtonHidden: Boolean = false,
    )

    val pets: List<Pet>
        get() = petsInteractor.getAllPets()

    fun floatingButtonPressed(screen: Screen): Screen? {
        return when (screen) {
            Screen.PETS_LIST -> createNewPetProfile()
            else -> null
        }
    }

    fun isFloatingButtonEnabled(screen: Screen): Boolean {
        return screen == Screen.PETS_LIST
    }

    fun createNewPetProfile(): Screen {
        return Screen.CREATE_PROFILE
    }
}
