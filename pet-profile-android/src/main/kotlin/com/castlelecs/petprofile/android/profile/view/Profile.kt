package com.castlelecs.petprofile.android.profile.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import com.castlelecs.petprofile.android.profile.viewmodel.PetsProfileViewModel
import com.castlelecs.petprofile.models.Breed
import com.castlelecs.petprofile.models.Gender
import com.castlelecs.petprofile.models.Pet

@Composable
fun Profile(vm: PetsProfileViewModel, modifier: Modifier = Modifier) {
    val state = vm.stateFlow.collectAsState().value

    Profile(
        name = state.name,
        lastName = state.name,
        gender = state.gender,
        dateOfBirth = state.dateOfBirth,
        breed = state.breed,
        isCastrated = state.isCastrated,
        changeFirstName = vm::changeFirstName,
        changeLastName = vm::changeLastName,
        toggleCastration = vm::toggleCastration,
        changeGender = vm::changeGender,
        pickDateOfBirth = vm::pickDateOfBirth,
        pickBreed = vm::pickBreed,
        save = vm::save,
        discardCurrentState = vm::discardCurrentState,
        modifier = modifier,
    )
}

@Composable
fun Profile(
    name: String = "",
    lastName: String = "",
    gender: Gender? = null,
    dateOfBirth: String? = null,
    breed: Breed = Breed.NONE,
    isCastrated: Boolean = false,
    changeFirstName: (String) -> Unit = {},
    changeLastName: (String) -> Unit = {},
    toggleCastration: () -> Unit = {},
    changeGender: (Gender?) -> Unit = {},
    pickDateOfBirth: (String?) -> Unit = {},
    pickBreed: (Breed) -> Unit = {},
    save: () -> Unit = {},
    discardCurrentState: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text("Name: $name")
        Text("Last name: $lastName")
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile()
}
