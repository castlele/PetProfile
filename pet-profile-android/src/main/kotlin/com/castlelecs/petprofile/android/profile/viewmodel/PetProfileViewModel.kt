package com.castlelecs.petprofile.android.profile.viewmodel

import com.castlelecs.petprofile.android.utils.SingleStateViewModel
import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.models.Activity
import com.castlelecs.petprofile.models.Breed
import com.castlelecs.petprofile.models.Gender
import kotlinx.coroutines.flow.MutableStateFlow

class PetProfileViewModel(
    private val petsInteractor: PetsInteractor,
) : SingleStateViewModel<PetProfileViewModel.State>() {

    data class State(
        val name: String = "",
        val lastName: String = "",
        val gender: Gender? = null,
        val dateOfBirth: String? = null,
        val breed: Breed? = null,
        val isCastrated: Boolean = false,
        val activities: Set<Activity> = emptySet(),
    )

    override val mutableStateFlow = MutableStateFlow(State())

    fun changeFirstName(name: String) {}

    fun changeLastName(name: String) {}

    fun toggleCastration() {}

    fun selectGender(gender: Gender) {}

    fun pickDateOfBirth(date: String?) {}

    fun pickBreed(breed: Breed?) {}

    fun save() {}

    fun close() {}

    fun discardCurrentState() {}
}
