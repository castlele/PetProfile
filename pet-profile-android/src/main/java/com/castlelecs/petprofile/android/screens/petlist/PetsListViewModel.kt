package com.castlelecs.petprofile.android.screens.petlist

import com.castlelecs.petprofile.android.helpers.BaseViewModel
import com.castlelecs.petprofile.interactors.PetsInteractor
import com.castlelecs.petprofile.models.Pet

class PetsListViewModel(
    private val petsInteractor: PetsInteractor
) : BaseViewModel<PetsListViewModel.State>(State()) {
    class State

    val pets: List<Pet>
        get() = petsInteractor.getAllPets()
}