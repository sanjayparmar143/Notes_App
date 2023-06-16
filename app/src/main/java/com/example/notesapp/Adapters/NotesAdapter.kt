package com.example.notesapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Entity.NotesEntity
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNoteBinding

class NotesAdapter(updatePin : (NotesEntity) -> Unit) : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    var updatePin = updatePin
    var notes = ArrayList<NotesEntity>()

    class NotesHolder(itemView: ItemNoteBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        var binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.binding.apply {
            txttitle.isSelected = true
            notes.get(position).apply {
                txttitle.text = title
                txttext.text = text

                if (pin) {

                    pinkey.setImageResource(R.drawable.pin)

                } else {

                    pinkey.setImageResource(R.drawable.unpin)
                }
            }
            pinkey.setOnClickListener {

                updatePin.invoke(notes.get(position))
            }
        }
    }

    fun update(notes: List<NotesEntity>) {
        this.notes = notes as ArrayList<NotesEntity>
        notifyDataSetChanged()
    }

    fun setNotes(notes: List<NotesEntity>) {
        this.notes = notes as ArrayList<NotesEntity>


    }
}

