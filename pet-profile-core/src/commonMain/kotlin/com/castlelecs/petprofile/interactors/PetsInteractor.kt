package com.castlelecs.petprofile.interactors

import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repository.PetsRepository
import com.castlelecs.utils.generateUUIDString

interface PetsInteractor {
    val petsRepository: PetsRepository

    fun getPet(id: ID): Pet?
    fun getAllPets(): List<Pet>

    fun createPet(): Pet
    fun removePet(pet: Pet)
}

class PetsInteractorImpl(
    override val petsRepository: PetsRepository,
) : PetsInteractor {

    override fun getPet(id: ID): Pet? {
        return petsRepository.get(id)
    }

    override fun getAllPets(): List<Pet> {
        return petsRepository.getAll()
    }

    override fun createPet(): Pet {
        val id = generateUUIDString()
        val pet = Pet.EMPTY(id = id)

        petsRepository.set(id, pet)

        return pet
    }

    override fun removePet(pet: Pet) {
        petsRepository.remove(pet.id)
    }
}
