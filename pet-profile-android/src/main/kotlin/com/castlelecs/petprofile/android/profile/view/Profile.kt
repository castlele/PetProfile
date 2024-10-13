package com.castlelecs.petprofile.android.profile.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.castlelecs.petprofile.android.R
import com.castlelecs.petprofile.android.profile.viewmodel.PetsProfileViewModel
import com.castlelecs.petprofile.android.ui.forms.FormTextField
import com.castlelecs.petprofile.models.Breed
import com.castlelecs.petprofile.models.Gender

@Composable
fun Profile(vm: PetsProfileViewModel, modifier: Modifier = Modifier) {
    val state = vm.stateFlow.collectAsState().value

    Profile(
        name = state.name,
        lastName = state.lastName,
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
    Box(modifier = modifier) {
        Column(
            verticalArrangement =
                Arrangement.spacedBy(dimensionResource(R.dimen.small_dp)),
            modifier =
                Modifier.padding(
                    horizontal = dimensionResource(R.dimen.default_dp)
                ),
        ) {
            FormTextField(
                text = name,
                onTextChange = changeFirstName,
                label = stringResource(R.string.pets_name),
                modifier = Modifier.fillMaxWidth(),
            )

            FormTextField(
                text = lastName,
                onTextChange = changeLastName,
                label = stringResource(R.string.pets_last_name),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile()
}
