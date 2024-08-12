package com.castlelecs.petprofile.interactors

import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repository.PetsRepository
import com.castlelecs.utils.generateUUIDString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PetsInteractorTests {
    @Test
    fun `Pets interactor can add pet to the storage`() {
        val repository = createPetsRepository()
        val sut = createPetsInteractor(repository)

        val pet = sut.createPetAndSaveImmediately()

        assertEquals(repository.get(pet.id), pet)
    }

    @Test
    fun `Pets interactor can remove pet from the storage`() {
        val pet = createPet()
        val repository = createPetsRepository(pet)
        val sut = createPetsInteractor(repository)

        sut.removePet(pet)

        assertTrue(repository.getAll().isEmpty())
    }

    @Test
    fun `Getting of the pet that is not in the storage do nothing`() {
        val pet = createPet()
        val repository = createPetsRepository()
        val sut = createPetsInteractor(repository)

        val gettedPet = sut.getPet(pet.id)

        assertEquals(null, gettedPet)
    }

    @Test
    fun `Getting of the pet that is in the storage will return it`() {
        val cat = createPet(CAT_NAME)
        val dog = createPet(DOG_NAME)
        val repository = createPetsRepository(cat, dog)
        val sut = createPetsInteractor(repository)

        val gettedCat = sut.getPet(cat.id)
        val gettedDog = sut.getPet(dog.id)

        assertEquals(cat, gettedCat)
        assertEquals(dog, gettedDog)
    }

    @Test
    fun `Interactor returns can return a list of all the pets in the storage`() {
        val pets = listOf(
            DEFAULT_PET_NAME, CAT_NAME, DOG_NAME,
        ).map { createPet(it) }
        val repository = createPetsRepository(*pets.toTypedArray())
        val sut = createPetsInteractor(repository)

        val listOfPets = sut.getAllPets()

        assertListsOfPetsEqual(pets, listOfPets)
    }

    @Test
    fun `Interactor should override pet if it was saved earlier`() {
        val pet = createPet()
        val repository = createPetsRepository(pet)
        val updatedPet = pet.copy(name = "Shanty")
        val sut = createPetsInteractor(repository)

        sut.savePet(updatedPet)

        // After `updatedPet` was saved,
        // the amount of pets in the repo should stay the same
        assertEquals(1, sut.getAllPets().count())
        assertEquals(updatedPet, sut.getPet(pet.id))
    }

    private fun createPetsInteractor(repository: PetsRepository): PetsInteractor {
        return PetsInteractorImpl(repository)
    }

    private fun createPetsRepository(vararg pets: Pet): PetsRepository {
        val storage = mutableMapOf<ID, Pet>()

        pets.forEach { storage[it.id] = it }

        return PetsRepository(storage)
    }

    private fun createPet(name: String = DEFAULT_PET_NAME): Pet {
        return Pet(
            id = generateUUIDString(),
            name = name,
        )
    }

    private fun PetsInteractor.createPetAndSaveImmediately(): Pet {
        return createPet().also { savePet(it) }
    }

    private fun assertListsOfPetsEqual(expected: List<Pet>, actual: List<Pet>) {
        assertEquals(expected.count(), actual.count())

        val mutableActual = actual.toMutableList()

        expected.forEach { expectedPet ->
            assertTrue(mutableActual.remove(expectedPet))
        }

        assertTrue(mutableActual.isEmpty())
    }

    companion object {
        private const val DEFAULT_PET_NAME = "Javie"
        private const val DOG_NAME = "Shanty"
        private const val CAT_NAME = "Perry"
    }
}

