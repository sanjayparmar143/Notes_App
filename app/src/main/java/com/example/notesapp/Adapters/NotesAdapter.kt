package com.example.notesapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Entity.NotesEntity
import com.example.notesapp.databinding.ItemNoteBinding

class NotesAdapter(notes : List<NotesEntity>) : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    var notes = notes
    class  NotesHolder(itemView: ItemNoteBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        var binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.binding.apply {
            notes.get(position).apply {
                txttitle.text = title
                txttext.text = text
            }
        }
    }

    fun update(notes: List<NotesEntity>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}

