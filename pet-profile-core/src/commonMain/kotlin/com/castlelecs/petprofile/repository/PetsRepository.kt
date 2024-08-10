package com.castlelecs.petprofile.repository

import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.utils.repository.BaseRepository

class PetsRepository(
    storage: MutableMap<ID, Pet>
) : BaseRepository<ID, Pet>(storage)

