package com.castlelecs.petprofile.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Reminder(
    val date: LocalDate,
    val time: LocalTime? = null,
    val repeateInfo: Frequency = Frequency.Never(),
    val endDate: EndRepeate = EndRepeate.Never(),
)
