package com.ahmetkaragunlu.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import com.ahmetkaragunlu.noteapp.model.NoteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NoteViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    private val _noteList = MutableStateFlow<List<NoteUiState>>(emptyList())
    val noteList = _noteList.asStateFlow()

    fun updateTitle(newTitle: String) {
        if (newTitle.all { it.isLetter() || it.isWhitespace() }) {
            _uiState.update { currentState ->
                currentState.copy(title = newTitle)
            }
        }

    }

    fun updateDescription(newDescription: String) {
        if (newDescription.all { it.isLetter() || it.isWhitespace() }) {
            _uiState.update { currentState ->
                currentState.copy(description = newDescription)
            }
        }
    }

    fun saveNote() {
        _noteList.update { currentState ->
            currentState + NoteUiState(
                title = _uiState.value.title,
                description = _uiState.value.description
            )
        }
        _uiState.update { curentState ->
            curentState.copy(title = "", description = "")
        }
    }

    fun deleteNote(note: NoteUiState) {
        _noteList.update { currentList ->
            currentList.filterNot { it == note }
        }

    }


}



