package com.castlelecs.petprofile.android.profile.datastores

import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.ID
import com.castlelecs.utils.datastore.DataStore

class ActivitiesDataStore : DataStore<ID, Activity> {

    private val storage = mutableMapOf<ID, Activity>()

    override fun get(key: ID): Activity? {
        return storage[key]
    }

    override fun set(key: ID, value: Activity) {
        storage[key] = value
    }

    override fun getAll(): List<Activity> {
        return storage.values.toList()
    }

    override fun remove(key: ID): Activity? {
        return storage.remove(key)
    }
}
