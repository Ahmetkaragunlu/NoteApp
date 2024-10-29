package com.ahmetkaragunlu.noteapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmetkaragunlu.noteapp.components.NoteInputText
import com.ahmetkaragunlu.noteapp.components.SaveButton
import com.ahmetkaragunlu.noteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(modifier: Modifier = Modifier, viewModel: NoteViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.jet_note_test), fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.LightGray,
                titleContentColor = Color.Black
            )
        )
        Column(
            modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NoteInputText(
                inputText = uiState.title,
                onValue = { viewModel.updateTitle(it) },
                label = R.string.title,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                modifier = modifier.padding(8.dp),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Red
                )
            )
            NoteInputText(
                inputText = uiState.description,
                onValue = { viewModel.updateDescription(it) },
                label = R.string.description,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = modifier.padding(8.dp),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Red
                )
            )
            SaveButton(
                onCLick = { viewModel.saveNote() },
                text = R.string.save_button,
                enabled = uiState.title.isNotBlank() && uiState.description.isNotBlank()
            )
            HorizontalDivider()
            NoteCard()

        }
    }
}

@Composable
fun NoteCard(modifier: Modifier = Modifier, viewModel: NoteViewModel = viewModel()) {

    val noteList by viewModel.noteList.collectAsState()

    LazyColumn {
        items(noteList) { note ->
            Card(
                modifier
                    .fillParentMaxWidth()
                    .padding(8.dp)
                    .clickable { viewModel.deleteNote(note) },
                shape = RoundedCornerShape(topEnd = 24.dp)
            ) {
                Column (modifier = modifier.padding(4.dp)){
                    Text(text = note.title, fontWeight = FontWeight.Bold)
                    Text(text = note.description)
                    Text(note.time)
                }
            }
        }
    }
}


