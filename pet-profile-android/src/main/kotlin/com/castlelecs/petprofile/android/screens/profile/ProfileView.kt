package com.castlelecs.petprofile.android.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.dimensionResource
import com.castlelecs.petprofile.android.screens.PetsViewModel
import com.castlelecs.petprofile.android.views.EditableText
import com.castlelecs.petprofile.android.R

@Composable
fun ProfileView(
    state: PetsViewModel.State,
    onSaveProfile: () -> Unit,
    onPetNameChanged: (String) -> Unit,
    onBackToViewingMode: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.default_dp))
    ) {
        EditableText(
            text = state.pet?.name ?: "",
            isEditing = state.isProfileInEditingMode,
            onTextChanged = onPetNameChanged,
            label = "Pet's name",
            modifier = Modifier.fillMaxWidth()
        )

        when (state.profileMode) {
            ProfileViewMode.EDITING -> {
                Button(
                    enabled = state.isSaveButtonEnabled,
                    onClick = {
                        onSaveProfile()
                        onBackToViewingMode()
                    },
                ) {
                    Text("Save Profile")
                }
            }
            ProfileViewMode.CREATING -> {
                Button(
                    enabled = state.isSaveButtonEnabled,
                    onClick = {
                        onSaveProfile()
                    },
                ) {
                    Text("Save Profile")
                }
            }
            else -> {}
        }
    }
}
