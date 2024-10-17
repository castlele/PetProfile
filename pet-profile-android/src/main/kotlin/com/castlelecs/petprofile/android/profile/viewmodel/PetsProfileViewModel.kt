package com.castlelecs.petprofile.android.profile.viewmodel

import com.castlelecs.petprofile.android.utils.SingleStateViewModel
import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.Breed
import com.castlelecs.petprofile.models.Gender
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.utils.generateUUIDString
import com.castlelecs.utils.logger.Logger
import com.castlelecs.utils.logger.compositeLogger
import com.castlelecs.utils.logger.error
import com.castlelecs.utils.logger.info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate

class PetsProfileViewModel(
    private val petsInteractor: PetsInteractor,
    private val logger: Logger = compositeLogger("PetsProfileViewModel"),
) : SingleStateViewModel<PetsProfileViewModel.State>() {

    data class State(
        val name: String = "",
        val lastName: String = "",
        val gender: Gender? = null,
        val dateOfBirth: String? = null,
        val breed: Breed = Breed.NONE,
        val isCastrated: Boolean = false,
        val activities: Set<Activity> = emptySet(),
    )

    override val mutableStateFlow = MutableStateFlow(State())

    fun changeFirstName(name: String) {
        state = state.copy(name = name)
    }

    fun changeLastName(lastName: String) {
        state = state.copy(lastName = lastName)
    }

    fun toggleCastration() {
        state = state.copy(isCastrated = state.isCastrated.not())
    }

    fun changeGender(gender: Gender?) {
        state = state.copy(gender = gender)
    }

    fun pickDateOfBirth(date: String?) {
        state = state.copy(dateOfBirth = date)
    }

    fun pickBreed(breed: Breed) {
        state = state.copy(breed = breed)
    }

    fun save() {
        val pet = createPet()

        logger.info(petCreatedInfoMessage(pet))

        petsInteractor.save(pet)
    }

    fun discardCurrentState() {
        state = State()
    }

    private fun createPet(): Pet {
        return Pet(
            id = generateUUIDString(),
            name = state.name,
            lastName = state.lastName,
            gender = state.gender,
            dateOfBirth = state.dateOfBirth?.toDate(),
            breed = state.breed,
            isCastrated = state.isCastrated,
            activities = state.activities,
        )
    }

    private fun String.toDate(): LocalDate? {
        return try {
            LocalDate.parse(this)
        } catch (_: Exception) {
            logger.error(dateParsingErrorMessage(this))
            null
        }
    }

    companion object {
        private fun dateParsingErrorMessage(date: String) =
            "Can't parse date from string: {$date}"

        private fun petCreatedInfoMessage(pet: Pet) = "Pet was created: {$pet}"
    }
}
