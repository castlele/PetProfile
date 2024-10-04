package com.castlelecs.petprofile.repositories

import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.utils.datastore.DataStore

class PetsRepositoryImpl(
    private val petsDataStore: DataStore<ID, Pet>,
    private val activitiesDataStore: DataStore<ID, Activity>,
) : PetsRepository {

    override fun savePet(pet: Pet) {
        petsDataStore.set(pet.id, pet)
    }

    override fun getAllPets(): List<Pet> {
        return petsDataStore.getAll()
    }

    override fun getPet(id: ID): Pet? {
        return petsDataStore.get(id)
    }

    override fun removePet(id: ID): Pet? {
        return petsDataStore.remove(id)
    }

    @Throws(SaveActivityException::class)
    override fun saveActivity(activity: Activity, pet: Pet) {
        if (getPet(pet.id) == null) {
            throw SaveActivityException.NoSavedPet(pet.id)
        }

        if (pet.id != activity.petId) {
            throw SaveActivityException.DifferentPetId(
                petId = pet.id,
                expectedPetId = activity.petId,
                activityId = activity.id
            )
        }

        activitiesDataStore.set(activity.id, activity)
    }

    override fun getAllActivities(): List<Activity> {
        return activitiesDataStore.getAll()
    }

    override fun getActivity(id: ID): Activity? {
        return activitiesDataStore.get(id)
    }

    override fun getActivities(pet: Pet): List<Activity> {
        return getAllActivities()
            .filter { it.petId == pet.id }
    }

    override fun removeActivity(id: ID): Activity? {
        return activitiesDataStore.remove(id)
    }
}
