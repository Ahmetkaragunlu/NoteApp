package com.ahmetkaragunlu.noteapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


@Composable
fun NoteInputText(
    inputText: String,
    onValue: (String) -> Unit,
    @StringRes label: Int,
    maxLine: Int = 1,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    colors: TextFieldColors
) {
    TextField(
        value = inputText,
        onValueChange = onValue,
        label = { Text(stringResource(label)) },
        maxLines = maxLine,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        colors = colors
    )

}

@Composable
fun SaveButton(
    onCLick: () -> Unit,
    @StringRes text: Int,
    enabled: Boolean
) {
    Button(
        onClick = onCLick,
        enabled = enabled
    ) {
        Text(text = stringResource(text))
    }
}
