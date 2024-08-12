package com.castlelecs.petprofile.interactors

import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repository.PetsRepository

interface PetsInteractor {
    val petsRepository: PetsRepository

    fun getPet(id: ID): Pet?
    fun getAllPets(): List<Pet>

    fun savePet(pet: Pet)
    fun createPet(): Pet
    fun createPet(name: String): Pet
    fun removePet(pet: Pet)
}
