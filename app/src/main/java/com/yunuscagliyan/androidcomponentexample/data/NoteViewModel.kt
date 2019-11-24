package com.yunuscagliyan.androidcomponentexample.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel constructor(application: Application):AndroidViewModel(application) {
    private var noteRepository: NoteRepository = NoteRepository(application)
    private  var allNotes:LiveData<List<Note>>
    init {
        allNotes=noteRepository.getAllNotes()

    }
    fun insertNote(note: Note){
        noteRepository.insert(note)
    }
    fun deleteNote(note: Note){
        noteRepository.delete(note)
    }
    fun updateNote(note: Note){
        noteRepository.update(note)
    }
    fun deleteAllNotes(){
        noteRepository.deleteAllNotes()
    }
    fun findNote(id:Int):Note{
        return noteRepository.findNoteById(id)
    }
    fun getAllNotes():LiveData<List<Note>>{
        return allNotes
    }

}