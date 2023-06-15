package com.example.notesapp.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.Entity.NotesEntity

@Dao
interface NoteDao {

    @Insert
    fun addNote(noteDao: NotesEntity)

    @Query("SELECT * FROM notes")
    fun getNotes() : List<NotesEntity>

    @Update
    fun updateNote(notesEntity: NotesEntity)

    @Delete
    fun deleteNote(notesEntity: NotesEntity)

    @Query("DELETE FROM notes")
    fun allDelete()

}