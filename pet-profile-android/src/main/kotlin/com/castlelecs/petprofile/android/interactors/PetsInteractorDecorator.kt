package com.castlelecs.petprofile.android.interactors

import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repository.PetsRepository
import com.castlelecs.utils.logger.compositeLogger
import com.castlelecs.utils.logger.LogLevel

class PetsInteractorDecorator(
     private val petsInteractor: PetsInteractor,
): PetsInteractor {

    override val petsRepository = petsInteractor.petsRepository

    private val logger = compositeLogger(TAG)

    override fun savePet(pet: Pet) {
        petsInteractor.savePet(pet)

        logger.log(
            LogLevel.INFO,
            PET_SAVED_MESSAGE(pet),
        )
    }

    override fun getPet(id: ID): Pet? {
        return petsInteractor.getPet(id)
    }

    override fun getAllPets(): List<Pet> {
        return petsInteractor.getAllPets()
    }

    override fun createPet(): Pet {
        val pet = petsInteractor.createPet()

        logger.log(
            LogLevel.INFO,
            PET_CREATED_MESSAGE(pet),
        )

        return pet
    }

    override fun createPet(name: String): Pet {
        val pet = petsInteractor.createPet(name)

        logger.log(
            LogLevel.INFO,
            PET_CREATED_MESSAGE(pet),
        )

        return pet
    }

    override fun removePet(pet: Pet) {
        petsInteractor.removePet(pet)
    }

    companion object {
        private const val TAG = "PetsInteractorDecorator"

        private fun PET_SAVED_MESSAGE(pet: Pet) = "Pet was SAVED: ${pet}"
        private fun PET_CREATED_MESSAGE(pet: Pet) = "Pet was CREATED: ${pet}"
    }
}
