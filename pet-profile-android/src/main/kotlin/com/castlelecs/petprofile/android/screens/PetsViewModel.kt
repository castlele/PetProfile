package com.castlelecs.petprofile.android.screens

import com.castlelecs.petprofile.android.helpers.BaseViewModel
import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.android.screens.profile.ProfileViewMode
import com.castlelecs.petprofile.models.Activity
import com.castlelecs.utils.logger.LogLevel

class PetsViewModel(
    private val petsInteractor: PetsInteractor
) : BaseViewModel<PetsViewModel.State>(State()) {

    data class State(
        val pet: Pet? = null,
        val editingActivity: Activity? = null,
        val profileMode: ProfileViewMode? = null,
    ) {
        val isProfileInEditingMode: Boolean
            get() = profileMode == ProfileViewMode.EDITING ||
                profileMode == ProfileViewMode.CREATING

        val isSaveButtonEnabled: Boolean
            get() = pet != null && pet.name.isNotBlank()

        val isCreateProfileButtonHidden: Boolean
            get() = profileMode == null
    }

    val pets: List<Pet>
        get() = petsInteractor.getAllPets()

    fun onBackToPetsList() {
        mutableStateFlow.value = stateValue.copy(
            profileMode = null,
            pet = null,
        )
    }

    fun onEditPetsProfile() {
        mutableStateFlow.value = stateValue.copy(
            profileMode = ProfileViewMode.EDITING,
        )
    }

    fun onBackToViewingMode() {
        mutableStateFlow.value = stateValue.copy(
            profileMode = ProfileViewMode.VIEWING,
        )
    }

    fun createNewPetProfile() {
        mutableStateFlow.value = stateValue.copy(
            profileMode = ProfileViewMode.CREATING,
            pet = null
        )
    }

    fun onPetClicked(pet: Pet) {
        mutableStateFlow.value = stateValue.copy(
            profileMode = ProfileViewMode.VIEWING,
            pet = pet,
        )
    }

    fun onPetNameChanged(newName: String) {
        val pet = stateValue.pet

        mutableStateFlow.value = stateValue.copy(
            pet = updatePet(pet, newName),
        )
    }

    fun saveProfile() {
        stateValue.pet?.let {
            petsInteractor.savePet(
                pet = it.copy(name = it.name.trim())
            )
        }
    }

    fun onActivityAdded() {
        val activity = petsInteractor.createActivity("New Activity")

        mutableStateFlow.value = stateValue.copy(
            editingActivity = activity,
        )
    }

    fun onActivityChanged(activity: Activity) {
        mutableStateFlow.value = stateValue.copy(
            editingActivity = activity,
        )
    }

    fun onActivitySaved(activity: Activity) {
        mutableStateFlow.value = stateValue.copy(
            editingActivity = null,
            pet = updatePet(
                pet = stateValue.pet,
                activity = activity,
            ),
        )
    }

    private fun updatePet(
        pet: Pet?,
        name: String? = null,
        activity: Activity? = null,
    ): Pet {
        val localPet = wrapPet(pet)
        val updatedPet = localPet.copy(
            name = name ?: localPet.name,
            activities = if (activity != null) {
                localPet.activities + activity
            } else {
                localPet.activities
            },
        )

        logger.log(
            LogLevel.INFO,
            PET_UPDATED_MESSAGE(updatedPet)
        )

        return updatedPet
    }

    private fun wrapPet(pet: Pet?): Pet {
        return if ((pet) == null) {
            val createdPet = petsInteractor.createPet()

            logger.log(
                LogLevel.INFO,
                PET_CREATED_MESSAGE(createdPet)
            )

            createdPet

        } else {
            pet
        }
    }

    companion object {
        private fun PET_CREATED_MESSAGE(pet: Pet) = "Pet was CREATED: $pet"
        private fun PET_UPDATED_MESSAGE(pet: Pet) = "Pet was UPDATED: $pet"
    }
}
