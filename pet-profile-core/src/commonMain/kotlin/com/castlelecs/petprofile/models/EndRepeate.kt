package com.castlelecs.petprofile.models

import kotlinx.datetime.LocalDate

sealed class EndRepeate {
    class Never : EndRepeate()

    data class After(val times: Int) : EndRepeate()

    data class OnDate(val date: LocalDate) : EndRepeate()
}
