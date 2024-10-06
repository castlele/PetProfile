package com.castlelecs.petprofile.repositories

import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet

interface PetsRepository {
    fun savePet(pet: Pet)
    fun getAllPets(): List<Pet>
    fun getPet(id: ID): Pet?
    fun removePet(id: ID): Pet?

    @Throws(SaveActivityException::class)
    fun saveActivity(activity: Activity, pet: Pet)
    fun getActivity(id: ID): Activity?
    fun getAllActivities(): List<Activity>
    fun getActivities(pet: Pet): List<Activity>
    fun removeActivity(id: ID): Activity?
}

