package com.castlelecs.petprofile.utils

import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.mocks.MockDataStore
import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repositories.PetsRepository
import com.castlelecs.petprofile.repositories.PetsRepositoryImpl
import com.castlelecs.utils.datastore.DataStore

internal const val PET_ID = "pet-id"
internal const val ACTIVITY_ID = "activity-id"

internal fun createPetsInteractor(
    petsRepository: PetsRepository = createPetsRepository(),
): PetsInteractor {
    return PetsInteractorImpl(petsRepository)
}

internal fun createPetsRepository(
    pets: List<Pet>,
    activities: List<Activity>,
): PetsRepository {
    return PetsRepositoryImpl(
        petsDataStore = createPetsDataStore(*pets.toTypedArray()),
        activitiesDataStore = createActivitiesDataStore(*activities.toTypedArray()),
    )
}

internal fun createPetsRepository(
    petsDataStore: DataStore<ID, Pet> = createPetsDataStore(*createMultiplePets(0).toTypedArray()),
    activitiesDataStore: DataStore<ID, Activity> = createActivitiesDataStore(*createMultipleActivities("", 0).toTypedArray()),
): PetsRepository {
    return PetsRepositoryImpl(
        petsDataStore = petsDataStore,
        activitiesDataStore = activitiesDataStore,
    )
}

internal fun createPetsDataStore(vararg pets: Pet): DataStore<ID, Pet> {
    val store = MockDataStore<ID, Pet>()

    pets.forEach { pet ->
        store.set(pet.id, pet)
    }

    return store
}

internal fun createActivitiesDataStore(vararg activities: Activity): DataStore<ID, Activity> {
    val store = MockDataStore<ID, Activity>()

    activities.forEach { activity ->
        store.set(activity.id, activity)
    }

    return store
}

internal fun createMultiplePets(amount: Int = 5): List<Pet> {
    return (0..amount).map { index ->
        createPet(id = "$index")
    }
}

internal fun createMultipleActivities(petId: String, amount: Int = 5): List<Activity> {
    return (0..amount).map { index ->
        Activity.EMPTY(petId, "$index")
    }
}

internal fun createPet(
    id: String = PET_ID,
    name: String = "",
): Pet {
    return Pet(
        id = id,
        name = name,
    )
}

internal fun createActivity(
    petId: String,
    id: String = ACTIVITY_ID,
): Activity {
    return Activity.EMPTY(
        id = id,
        petId = petId,
    )
}
