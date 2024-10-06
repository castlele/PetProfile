package com.castlelecs.petprofile.utils

import kotlin.test.assertEquals
import kotlin.test.assertTrue

fun <T: Collection<Any>> assertSequencesEquals(
    expected: T,
    actual: T,
) {
    assertEquals(expected.count(), actual.count())

    val mutableActual = actual.toMutableList()

    expected.forEach { expectedPet ->
        assertTrue(mutableActual.remove(expectedPet))
    }

    assertTrue(mutableActual.isEmpty())
}
