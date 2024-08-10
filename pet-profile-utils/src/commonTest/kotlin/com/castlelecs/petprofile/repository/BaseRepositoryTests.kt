package com.castlelecs.petprofile.repository

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.collections.mutableMapOf

class BaseRepositoryTests {
    @Test
    fun `New repository is always empty`() {
        val sut = getRepository()

        assertTrue(sut.getAll().isEmpty())
    }

    @Test
    fun `Get element in empty repository always return null`() {
        val sut = getRepository()

        val value = sut.get("some key")

        assertEquals(null, value)
    }

    @Test
    fun `Remove of the element in empty repository do nothing`() {
        val sut = getRepository()

        sut.remove("some key")

        assertTrue(sut.getAll().isEmpty())
    }

    @Test
    fun `Set stores value in the repository`() {
        val value = "Value"
        val key = "Key"
        val sut = getRepository()

        sut.set(key, value)

        assertEquals(value, sut.get(key))
    }

    @Test
    fun `Remove method indicate success if value was removed`() {
        val value = "Value"
        val key = "Key"
        val sut = getRepository()
        sut.set(key, value)

        sut.remove(key)

        assertTrue(sut.getAll().isEmpty())
    }

    private fun getRepository(): Repository<String, String> {
        return BaseRepository(mutableMapOf())
    }
}
