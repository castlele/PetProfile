package com.castlelecs.utils.repository

open class BaseRepository<K, E>(
    private val storage: MutableMap<K, E>
) : Repository<K, E> {

    override fun get(key: K): E? {
        return storage[key]
    }

    override fun set(key: K, value: E) {
        storage[key] = value
    }

    override fun getAll(): List<E> {
        return storage.values.toList()
    }

    override fun remove(key: K) {
        storage.remove(key)
    }
}
