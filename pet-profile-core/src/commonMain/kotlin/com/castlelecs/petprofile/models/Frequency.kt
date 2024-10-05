package com.castlelecs.petprofile.models

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

sealed class Frequency {
    class Never : Frequency()
    class Daily : Frequency()
    class Weekly : Frequency()
    class Monthly : Frequency()
    class Yearly : Frequency()

    sealed class Custom : Frequency() {
        data class Daily(val every: Int) : Custom()
        data class Weekly(val every: Int, val weekday: Set<DayOfWeek>) : Custom()
        data class Monthly(val every: Int, val dayOfMonth: Set<Int>) : Custom()
        data class Yearly(val every: Int, val month: Set<Month>) : Custom()
    }
}
