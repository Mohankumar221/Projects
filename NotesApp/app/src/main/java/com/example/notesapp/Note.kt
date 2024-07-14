package com.example.notesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    val title: String,
    val description: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
