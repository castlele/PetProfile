package com.castlelecs.petprofile.interactors

import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repositories.PetsRepository
import com.castlelecs.utils.generateUUIDString

class PetsInteractorImpl(
    // override val petsRepository: PetsRepository,
) : PetsInteractor {
    //
    // override fun savePet(pet: Pet) {
    //     petsRepository.set(pet.id, pet)
    // }
    //
    // override fun getPet(id: ID): Pet? {
    //     return petsRepository.get(id)
    // }
    //
    // override fun getAllPets(): List<Pet> {
    //     return petsRepository.getAll()
    // }
    //
    // override fun createPet(): Pet {
    //     val id = generateUUIDString()
    //     val pet = Pet.EMPTY(id = id)
    //
    //     return pet
    // }
    //
    // override fun createPet(name: String): Pet {
    //     return createPet().copy(name = name)
    // }
    //
    // override fun removePet(pet: Pet) {
    //     petsRepository.remove(pet.id)
    // }
    //
    // override fun createActivity(name: String): Activity {
    //     return Activity.EMPTY(name = name)
    // }
    //
    // override fun saveActivityForPet(pet: Pet, activity: Activity) {
    //     savePet(pet.copy(activities = pet.activities + activity))
    // }
}
