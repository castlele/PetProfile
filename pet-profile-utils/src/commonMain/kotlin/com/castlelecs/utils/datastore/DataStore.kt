package com.castlelecs.utils.datastore

interface DataStore<Key, Value> {
    fun get(key: Key): Value?
    fun set(key: Key, value: Value)
    fun getAll(): List<Value>
    fun remove(key: Key): Value?
}
