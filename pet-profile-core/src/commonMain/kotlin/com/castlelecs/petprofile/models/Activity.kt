package com.castlelecs.petprofile.models

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Activity(
    val name: String,
    val dateTime: LocalDateTime,
    val description: String = "",
    val frequency: Frequency? = null,
    val endDate: String? = null,
    val endTime: String? = null,
) {
    companion object {
        fun EMPTY(name: String) = Activity(
            name = name,
            dateTime = LocalDateTime.now(),
        )
    }
}

private fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}
