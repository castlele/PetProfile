package com.castlelecs.petprofile.android.screens.profile

import com.castlelecs.petprofile.android.helpers.BaseViewModel
import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.utils.logger.LogLevel

class ProfileViewModel(
    mode: ProfileViewMode,
    private val petsInteractor: PetsInteractor,
) : BaseViewModel<ProfileViewModel.State>(ProfileViewModel.State(null, mode)) {

    data class State(
        val pet: Pet? = null,
        val mode: ProfileViewMode,
    ) {
        val isSaveButtonEnabled: Boolean
            get() = pet != null && pet.name.isNotBlank()
    }

    fun onPetNameChanged(newName: String) {
        val pet = stateValue.pet

        mutableStateFlow.value = stateValue.copy(
            pet = updatePetWithName(pet, newName.trim()),
        )
    }

    // WARN: How to let the user know that pet is empty?
    fun saveProfile() {
        stateValue.pet?.let { petsInteractor.savePet(it) }
    }

    private fun updatePetWithName(pet: Pet?, name: String): Pet {
        return if (pet == null) {
            val createdPet = petsInteractor.createPet(name)

            logger.log(
                LogLevel.INFO,
                PET_CREATED_MESSAGE(createdPet)
            )

            createdPet

        } else {
            val updatedPet = pet.copy(name = name)

            logger.log(
                LogLevel.INFO,
                PET_UPDATED_MESSAGE(updatedPet)
            )

            updatedPet
        }
    }

    companion object {
        private fun PET_CREATED_MESSAGE(pet: Pet) = "Pet was CREATED: $pet"
        private fun PET_UPDATED_MESSAGE(pet: Pet) = "Pet was UPDATED: $pet"
    }
}
