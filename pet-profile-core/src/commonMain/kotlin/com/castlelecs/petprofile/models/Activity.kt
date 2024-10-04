package com.castlelecs.petprofile.models

import com.castlelecs.utils.generateUUIDString
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Activity(
    val petId: String,
    val id: String,
    val dateTime: LocalDateTime,
    val name: String = "",
    val description: String = "",
    val frequency: Frequency? = null,
    val endDate: String? = null,
    val endTime: String? = null,
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
            petId = petId,
            id = id,
            dateTime = LocalDateTime.now(),
        )
    }
}

private fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}
