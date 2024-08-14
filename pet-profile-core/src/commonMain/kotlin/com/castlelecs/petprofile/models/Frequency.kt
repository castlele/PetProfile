package com.castlelecs.petprofile.models

sealed class Frequency(val interval: Int) {
    class Day(interval: Int) : Frequency(interval)
    class Week(interval: Int) : Frequency(interval)
    class Month(interval: Int) : Frequency(interval)
    class Year(interval: Int) : Frequency(interval)
}
