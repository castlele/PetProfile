package com.castlelecs.petprofile.android.profile.datastores

import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.utils.datastore.DataStore

class PetsDataStore : DataStore<ID, Pet> {

    private val storage = mutableMapOf<ID, Pet>()

    override fun get(key: ID): Pet? {
        return storage[key]
    }

    override fun set(key: ID, value: Pet) {
        storage[key] = value
    }

    override fun getAll(): List<Pet> {
        return storage.values.toList()
    }

    override fun remove(key: ID): Pet? {
        return storage.remove(key)
    }
}
