package com.castlelecs.petprofile.android.ui.forms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FormTextField(
    text: String,
    label: String = "",
    onTextChange: (String) -> Unit,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = maxLines == 1,
            maxLines = maxLines,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
        )
    }
}
