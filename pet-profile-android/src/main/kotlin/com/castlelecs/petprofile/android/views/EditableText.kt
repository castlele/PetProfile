package com.castlelecs.petprofile.android.views

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EditableText(
    text: String,
    isEditing: Boolean,
    onTextChanged: (String) -> Unit,
    label: String? = null,
    modifier: Modifier = Modifier
) {
    if (isEditing) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            label = {
                if (label != null) {
                    Text(label)
                }
            },
            modifier = modifier
        )

    } else {
        Text(
            text = text,
            modifier = modifier
        )
    }
}
