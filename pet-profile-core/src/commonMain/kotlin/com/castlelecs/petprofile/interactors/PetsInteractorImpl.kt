package com.castlelecs.petprofile.interactors

import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repositories.PetsRepository
import com.castlelecs.utils.generateUUIDString

class PetsInteractorImpl(
    override val petsRepository: PetsRepository,
) : PetsInteractor {

    override fun save(pet: Pet) {
        petsRepository.savePet(pet)
    }

    override fun delete(pet: Pet): Boolean {
         val deletedPet = petsRepository.removePet(pet.id)
            ?.let {
                petsRepository.getActivities(it).forEach { activity ->
                    petsRepository.removeActivity(activity.id)
                }
            }

         return deletedPet != null
    }

    override fun saveActivity(activity: Activity, pet: Pet) {
        try {
            petsRepository.saveActivity(activity, pet)
        } catch (_: Exception) {}
    }

    override fun deleteActivity(activity: Activity): Boolean {
        return petsRepository.removeActivity(activity.id) != null
    }
}
