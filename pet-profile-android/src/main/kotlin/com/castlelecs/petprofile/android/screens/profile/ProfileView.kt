package com.castlelecs.petprofile.android.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun ProfileView(
    viewModel: ProfileViewModel,
    onSaveProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsState().value

    when (state.mode) {
        ProfileViewMode.CREATING -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))

                    // TODO: Here should be a Image with a pet
                    Text("Hello")

                    Spacer(modifier = Modifier.weight(1f))
                }

                OutlinedTextField(
                    value = state.pet?.name ?: "",
                    onValueChange = viewModel::onPetNameChanged,
                    label = { Text("Pet's name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    enabled = state.isSaveButtonEnabled,
                    onClick = {
                        viewModel.saveProfile()
                        onSaveProfile()
                    },
                ) {
                    Text("Save Profile")
                }
            }
        }
    }
}
