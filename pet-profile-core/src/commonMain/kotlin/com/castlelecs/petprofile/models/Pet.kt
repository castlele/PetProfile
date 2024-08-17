package com.castlelecs.petprofile.models

data class Pet(
    val id: ID,
    val name: String,
    val activities: Set<Activity> = emptySet(),
) {
    companion object {
        fun EMPTY(id: ID): Pet {
            return Pet(
                id = id,
                name = "",
            )
        }
    }
}
