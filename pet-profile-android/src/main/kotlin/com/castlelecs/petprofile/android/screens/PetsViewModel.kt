package com.castlelecs.petprofile.android.screens

import com.castlelecs.petprofile.android.helpers.BaseViewModel
import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.android.screens.profile.ProfileViewMode
import com.castlelecs.petprofile.android.screens.profile.ProfileViewModel
import com.castlelecs.utils.logger.LogLevel

class PetsViewModel(
    private val petsInteractor: PetsInteractor
) : BaseViewModel<PetsViewModel.State>(State()) {

    class State

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

    fun createProfileViewModelWithMode(
        mode: ProfileViewMode
    ): ProfileViewModel {
        val profileViewModel = ProfileViewModel(
            mode = mode,
            petsInteractor = petsInteractor,
        )

        logger.log(
            LogLevel.INFO,
            PROFILE_VIEW_MODEL_CREATED_MESSAGE(mode)
        )

        return profileViewModel
    }

    companion object {
        private fun PROFILE_VIEW_MODEL_CREATED_MESSAGE(
            mode: ProfileViewMode
        ) = "ProfileViewModel created with mode: $mode"
    }
}
