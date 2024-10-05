package com.castlelecs.petprofile.models

import com.castlelecs.petprofile.utils.now
import kotlinx.datetime.LocalDateTime

data class Activity(
    val id: String,
    val petId: String,
    val name: String,
    val reminder: Reminder,
    val description: String = "",
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (other !is Activity) return false

        return id == other.id && petId == other.petId
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun EMPTY(petId: String, id: String) = Activity(
            id = id,
            petId = petId,
            name = "",
            reminder = Reminder(
                date = LocalDateTime.now().date,
            ),
        )
    }
}

