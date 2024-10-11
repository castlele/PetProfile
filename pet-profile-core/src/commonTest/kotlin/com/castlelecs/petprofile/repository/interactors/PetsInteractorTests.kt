package com.castlelecs.petprofile.interactors

import com.castlelecs.petprofile.utils.createActivity
import com.castlelecs.petprofile.utils.createMultipleActivities
import com.castlelecs.petprofile.utils.createPet
import com.castlelecs.petprofile.utils.createPetsInteractor
import com.castlelecs.petprofile.utils.createPetsRepository
import kotlin.collections.emptyList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PetsInteractorTests {

    @Test
    fun savingNewPetCreatesItInDataStore() {
        val pet = createPet()
        val repository = createPetsRepository()
        val sut = createPetsInteractor(repository)

        sut.save(pet)

        assertEquals(pet, repository.getPet(pet.id))
    }

    @Test
    fun savingPetThatIsInDataStoreOverridesIt() {
        val pet = createPet()
        val updatedPet = createPet(name = "Hello")
        val repository = createPetsRepository(listOf(pet), emptyList())
        val sut = createPetsInteractor(repository)

        sut.save(updatedPet)

        assertEquals(updatedPet, repository.getPet(pet.id))
        assertEquals(1, repository.getAllPets().count())
    }

    @Test
    fun deletionOfPetThatIsNotInDataStoreDoNothing() {
        val sut = createPetsInteractor()

        val isDeleted = sut.delete(createPet())

        assertFalse(isDeleted)
    }

    @Test
    fun deletionOfThePetRemovesItFromDataStore() {
        val pet = createPet()
        val repository =
            createPetsRepository(pets = listOf(pet), activities = emptyList())
        val sut = createPetsInteractor(repository)

        val isDeleted = sut.delete(pet)

        assertTrue(isDeleted)
    }

    @Test
    fun deletionOfThePetRemovesItFromDataStoreAndRemovesAllItsActivities() {
        val pet = createPet()
        val activities = createMultipleActivities(pet.id)
        val repository = createPetsRepository(listOf(pet), activities)
        val sut = createPetsInteractor(repository)

        val isDeleted = sut.delete(pet)

        assertTrue(isDeleted)
        assertEquals(emptyList(), repository.getAllPets())
        assertEquals(emptyList(), repository.getAllActivities())
    }

    @Test
    fun savingNewActivityCreatesItInDataStore() {
        val pet = createPet()
        val activity = createActivity(pet.id)
        val repository = createPetsRepository(listOf(pet), emptyList())
        val sut = createPetsInteractor(repository)

        sut.saveActivity(activity, pet)

        assertEquals(activity, repository.getActivity(activity.id))
    }

    @Test
    fun savingActivityThatIsInDataStoreOverridesIt() {
        val pet = createPet()
        val activity = createActivity(pet.id)
        val updatedActivity = activity.copy(name = "Hello")
        val repository = createPetsRepository(listOf(pet), listOf(activity))
        val sut = createPetsInteractor(repository)

        sut.saveActivity(updatedActivity, pet)

        assertEquals(
            updatedActivity,
            repository.getActivity(updatedActivity.id),
        )
        assertEquals(1, repository.getAllActivities().count())
    }

    @Test
    fun deletionOfActivityThatIsNotInDataStoreDoNothing() {
        val pet = createPet()
        val activity = createActivity(pet.id)
        val repository = createPetsRepository(emptyList(), emptyList())
        val sut = createPetsInteractor(repository)

        val isDeleted = sut.deleteActivity(activity)

        assertFalse(isDeleted)
    }

    @Test
    fun deletionOfTheActivityRemovesItFromDataStore() {
        val pet = createPet()
        val activity = createActivity(pet.id)
        val repository = createPetsRepository(listOf(pet), listOf(activity))
        val sut = createPetsInteractor(repository)

        val isDeleted = sut.deleteActivity(activity)

        assertTrue(isDeleted)
    }
}
