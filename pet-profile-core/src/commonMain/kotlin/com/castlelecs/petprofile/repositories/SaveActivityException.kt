package com.castlelecs.petprofile.repositories

sealed class SaveActivityException(message: String) : Exception(message) {

    class NoSavedPet(id: String) : SaveActivityException(NO_SAVED_PET_MESSAGE(id))

    class DifferentPetId(
        petId: String,
        expectedPetId: String,
        activityId: String,
    ) : SaveActivityException(DIFFERENT_PET_ID(petId, expectedPetId, activityId))

    companion object {
        private fun NO_SAVED_PET_MESSAGE(id: String) = "No pet saved with id {$id} in data store."
        private fun DIFFERENT_PET_ID(
            petId: String,
            expectedPetId: String,
            activityId: String,
        ) = "Activity with id {$activityId} has different pet id. Expected: $expectedPetId, Actual: $petId"
    }
}
