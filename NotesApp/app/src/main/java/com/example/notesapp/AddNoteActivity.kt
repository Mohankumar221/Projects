package com.example.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityAddNoteBinding
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val intent = intent
        if (intent.hasExtra("note_id")) {
            noteId = intent.getIntExtra("note_id", -1)
            val noteTitle = intent.getStringExtra("note_title")
            val noteDescription = intent.getStringExtra("note_description")
            binding.editTextTitle.setText(noteTitle)
            binding.editTextDescription.setText(noteDescription)
        }

        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                if (noteId != -1) {
                    val updatedNote = Note(title, description, noteId)
                    noteViewModel.update(updatedNote)
                } else {
                    val newNote = Note(title, description)
                    noteViewModel.insert(newNote)
                }
                finish()
            }
        }
    }
}
