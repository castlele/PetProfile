package com.castlelecs.petprofile.repositories

import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.utils.assertSequencesEquals
import com.castlelecs.petprofile.utils.createActivitiesDataStore
import com.castlelecs.petprofile.utils.createMultipleActivities
import com.castlelecs.petprofile.utils.createMultiplePets
import com.castlelecs.petprofile.utils.createPetsDataStore
import com.castlelecs.petprofile.utils.createPetsRepository
import com.castlelecs.petprofile.utils.createPet
import com.castlelecs.petprofile.utils.PET_ID
import com.castlelecs.petprofile.utils.ACTIVITY_ID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PetsRepositoryTests {

    @Test
    fun petSavesInDataStore() {
        val id = PET_ID
        val pet = Pet.EMPTY(id)
        val dataStore = createPetsDataStore()
        val sut = createPetsRepository(dataStore)

        sut.savePet(pet)

        assertEquals(pet, dataStore.get(id))
    }

    @Test
    fun petCanBeFoundById() {
        val id = PET_ID
        val savedPet = Pet.EMPTY(id)
        val sut = createPetsRepository(
            pets = listOf(savedPet),
            activities = emptyList(),
        )

        val pet = sut.getPet(id)

        assertEquals(savedPet, pet)
    }

    @Test
    fun allPetsCanBeGet() {
        val expectedPets = createMultiplePets()
        val sut = createPetsRepository(
            pets = expectedPets,
            activities = emptyList(),
        )

        val pets = sut.getAllPets()

        assertSequencesEquals(expectedPets, pets)
    }

    @Test
    fun noActionOnDeletionOfPetFromEmptyRepository() {
        val id = PET_ID
        val dataStore = createPetsDataStore()
        val sut = createPetsRepository(dataStore)

        val deletedPet = sut.removePet(id)

        assertEquals(null, deletedPet)
    }

    @Test
    fun petCanBeDeleted() {
        val id = PET_ID
        val pet = Pet.EMPTY(id)
        val dataStore = createPetsDataStore(pet)
        val sut = createPetsRepository(dataStore)

        val deletedPet = sut.removePet(id)

        assertEquals(pet, deletedPet)
        assertEquals(null, sut.getPet(id))
    }

    @Test
    fun noActionOnDeletionOfPetThatIsNotInRepository() {
        val id = PET_ID
        val idNotInRepository = PET_ID + PET_ID
        val pet = Pet.EMPTY(id)
        val dataStore = createPetsDataStore(pet)
        val sut = createPetsRepository(dataStore)

        val deletedPet = sut.removePet(idNotInRepository)

        assertEquals(null, deletedPet)
        assertEquals(null, sut.getPet(idNotInRepository))
    }

    @Test
    fun activitySavesInDataStoreWithExistingPetsId() {
        val petId = PET_ID
        val activityId = ACTIVITY_ID
        val pet = Pet.EMPTY(petId)
        val activity = Activity.EMPTY(petId, activityId)
        val petsDataStore = createPetsDataStore(pet)
        val activitiesDataStore = createActivitiesDataStore()
        val sut = createPetsRepository(
            petsDataStore = petsDataStore,
            activitiesDataStore = activitiesDataStore,
        )

        sut.saveActivity(activity, pet)

        assertEquals(activitiesDataStore.get(activityId), activity)
    }

    @Test
    fun savingActivityWithNonExistingPetsIdThrowsError() {
        val petId = PET_ID
        val activityId = ACTIVITY_ID
        val pet = Pet.EMPTY(petId)
        val activity = Activity.EMPTY(petId, activityId)
        val activitiesDataStore = createActivitiesDataStore()
        val sut = createPetsRepository(
            activitiesDataStore = activitiesDataStore,
        )

        assertFailsWith(
            SaveActivityException.NoSavedPet::class,
            { sut.saveActivity(activity, pet) },
        )
    }

    @Test
    fun savingActivityWithActivityNoLinkedToPetWithPetIdThrowsError() {
        val petId = PET_ID
        val activityId = ACTIVITY_ID
        val pet = Pet.EMPTY(petId)
        val activity = Activity.EMPTY(activityId, activityId)
        val petsDataStore = createPetsDataStore(pet)
        val activitiesDataStore = createActivitiesDataStore()
        val sut = createPetsRepository(
            petsDataStore = petsDataStore,
            activitiesDataStore = activitiesDataStore,
        )

        assertFailsWith(
            SaveActivityException.DifferentPetId::class,
            { sut.saveActivity(activity, pet) },
        )
    }

    @Test
    fun activityCanBeFoundById() {
        val petId = PET_ID
        val activityId = ACTIVITY_ID
        val pet = Pet.EMPTY(petId)
        val expectedActivities = Activity.EMPTY(petId, activityId)
        val sut = createPetsRepository(
            pets = listOf(pet),
            activities = listOf(expectedActivities),
        )

        val activity = sut.getActivity(activityId)

        assertEquals(expectedActivities, activity)
    }

    @Test
    fun allActivitiesCanBeFoundForPet() {
        val pets = createMultiplePets()
        val pet = pets[0]
        val expectedActivities = createMultipleActivities(pet.id)
        val sut = createPetsRepository(
            pets = pets,
            activities = expectedActivities,
        )

        val activities = sut.getActivities(pet)

        assertSequencesEquals(expectedActivities, activities)
    }

    @Test
    fun allActivitiesCanBeGet() {
        val pets = createMultiplePets()
        val expectedActivities = createMultipleActivities(pets[0].id)
        val sut = createPetsRepository(
            pets = pets,
            activities = expectedActivities,
        )

        val activities = sut.getAllActivities()

        assertSequencesEquals(expectedActivities, activities)
    }

    @Test
    fun noActionOnDeletionOfActivityFromEmptyRepository() {
        val petId = PET_ID
        val activityId = ACTIVITY_ID
        val pet = Pet.EMPTY(petId)
        val petsDataStore = createPetsDataStore(pet)
        val activitiesDataStore = createActivitiesDataStore()
        val sut = createPetsRepository(
            petsDataStore = petsDataStore,
            activitiesDataStore = activitiesDataStore,
        )

        val deletedActivity = sut.removeActivity(activityId)

        assertEquals(null, deletedActivity)
        assertEquals(null, sut.getActivity(activityId))
    }

    @Test
    fun activityCanBeDeleted() {
        val petId = PET_ID
        val activityId = ACTIVITY_ID
        val pet = Pet.EMPTY(petId)
        val activity = Activity.EMPTY(petId, activityId)
        val petsDataStore = createPetsDataStore(pet)
        val activitiesDataStore = createActivitiesDataStore(activity)
        val sut = createPetsRepository(
            petsDataStore = petsDataStore,
            activitiesDataStore = activitiesDataStore,
        )

        val deletedActivity = sut.removeActivity(activityId)

        assertEquals(activity, deletedActivity)
        assertEquals(null, sut.getActivity(activityId))
    }

    @Test
    fun noActionOnDeletionOfActivityThatIsNotInRepository() {
        val petId = PET_ID
        val activityId = ACTIVITY_ID
        val pet = Pet.EMPTY(petId)
        val petsDataStore = createPetsDataStore(pet)
        val activitiesDataStore = createActivitiesDataStore(
            *createMultipleActivities(petId).toTypedArray(),
        )
        val sut = createPetsRepository(
            petsDataStore = petsDataStore,
            activitiesDataStore = activitiesDataStore,
        )

        val deletedActivity = sut.removeActivity(activityId)

        assertEquals(null, deletedActivity)
        assertEquals(null, sut.getActivity(activityId))
    }
}
