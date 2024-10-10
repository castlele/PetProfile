package com.castlelecs.petprofile.interactors

import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repositories.PetsRepository

interface PetsInteractor {
    val petsRepository: PetsRepository

    fun save(pet: Pet)

    fun delete(pet: Pet): Boolean

    fun saveActivity(activity: Activity, pet: Pet)

    fun deleteActivity(activity: Activity): Boolean
}
