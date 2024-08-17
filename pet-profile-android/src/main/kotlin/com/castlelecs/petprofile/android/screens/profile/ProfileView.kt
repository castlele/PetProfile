package com.castlelecs.petprofile.android.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.castlelecs.petprofile.android.R
import com.castlelecs.petprofile.android.screens.PetsViewModel
import com.castlelecs.petprofile.android.views.EditableText
import com.castlelecs.petprofile.models.Activity

@Composable
fun ProfileView(
    state: PetsViewModel.State,
    onSaveProfile: () -> Unit,
    onPetNameChanged: (String) -> Unit,
    onActivityAdded: () -> Unit,
    onActivityChanged: (Activity) -> Unit,
    onActivitySaved: (Activity) -> Unit,
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

        state.pet?.activities?.forEach { activity ->
            ActivityView(
                activity = activity,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = onActivityAdded,
        ) {
            Text("Add Activity")
        }

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

    if (state.editingActivity != null) {
        ActivityEditorBottomSheet(
            activity = state.editingActivity,
            onActivityChanged = onActivityChanged,
            onDismissRequest = onActivitySaved,
        )
    }
}

@Composable
fun ActivityView(
    activity: Activity,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(activity.name)

        Text(activity.dateTime.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityEditorBottomSheet(
    activity: Activity,
    onActivityChanged: (Activity) -> Unit,
    onDismissRequest: (Activity) -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = {
            onDismissRequest(activity)
        },
    ) {
        EditableText(
            text = activity.name,
            isEditing = true,
            onTextChanged = { name ->
                 onActivityChanged(activity.copy(name = name))
            },
        )
    }
}
