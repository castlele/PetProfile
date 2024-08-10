package com.castlelecs.utils.repository

interface Repository<K, E> {
    fun get(key: K): E?
    fun set(key: K, value: E)
    fun getAll(): List<E>

    fun remove(key: K)
}
