package com.castlelecs.petprofile.mocks

import com.castlelecs.utils.datastore.DataStore

class MockDataStore<Key, Value> : DataStore<Key, Value> {

    private val storage = mutableMapOf<Key, Value>()

    constructor(initialValue: Map<Key, Value> = emptyMap()) {
        initialValue.forEach { key, value ->
            storage[key] = value
        }
    }

    override fun get(key: Key): Value? {
        return storage[key]
    }

    override fun set(key: Key, value: Value) {
        storage[key] = value
    }

    override fun getAll(): List<Value> {
        return storage.values.toList()
    }

    override fun remove(key: Key): Value? {
        return storage.remove(key)
    }
}
