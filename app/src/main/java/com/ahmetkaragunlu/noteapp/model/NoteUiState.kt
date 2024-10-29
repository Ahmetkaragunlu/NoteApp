package com.ahmetkaragunlu.noteapp.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


data class NoteUiState(
    val title : String = "",
    val description : String = "",
    val time : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
)
