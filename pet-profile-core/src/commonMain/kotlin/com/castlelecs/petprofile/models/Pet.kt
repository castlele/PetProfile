package com.castlelecs.petprofile.models

import kotlinx.datetime.LocalDate

data class Pet(
    val id: ID,
    val name: String,
    val lastName: String,
    val gender: Gender?,
    val dateOfBirth: LocalDate?,
    val breed: Breed,
    val isCastrated: Boolean,
    val activities: Set<Activity> = emptySet(),
) {
    companion object {
        fun EMPTY(id: ID, name: String = ""): Pet {
            return Pet(
                id = id,
                name = name,
                lastName = "",
                gender = null,
                dateOfBirth = null,
                breed = Breed.NONE,
                isCastrated = false,
            )
        }
    }
}
