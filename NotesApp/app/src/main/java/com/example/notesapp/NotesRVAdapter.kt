package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context?, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    private val allNotes = ArrayList<Note>()

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.noteTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.noteDescription)
        val deleteBtn: ImageView = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.itemView.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        viewHolder.deleteBtn.setOnClickListener {
            listener.onDeleteClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.titleTextView.text = currentNote.title
        holder.descriptionTextView.text = currentNote.description
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(notesList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(notesList)
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter {
    fun onItemClicked(note: Note)
    fun onDeleteClicked(note: Note)
}
