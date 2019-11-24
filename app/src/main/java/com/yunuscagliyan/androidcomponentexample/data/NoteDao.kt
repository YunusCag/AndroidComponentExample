package com.yunuscagliyan.androidcomponentexample.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("Select * from note_table order by priority desc")
    fun getAllNotes():LiveData<List<Note>>
    @Query("Select * from note_table where note_id=:id")
    fun findNoteById(id:Int):Note
    @Insert
    fun addNote(note: Note)
    @Delete
    fun deleteNote(note: Note)
    @Query("Delete from note_table")
    fun deleteAllNotes()
    @Update
    fun updateNote(note: Note)

}