package com.castlelecs.petprofile

import kotlin.test.Test
import kotlin.test.assertEquals

class StubTest {
    @Test
    fun stubTestCase() {
        val res = returnHelloWorld()

        assertEquals("Hello, World", res)
    }
}
