package com.yunuscagliyan.androidcomponentexample.data

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData

class NoteRepository constructor(application: Application) {
    private  var noteDao: NoteDao
    private  var allNotes: LiveData<List<Note>>
    private lateinit var  note:Note
    init {
        var noteDatabase=NoteDatabase.getInstance(application)
        noteDao=noteDatabase.noteDao()
        allNotes=noteDao.getAllNotes()
    }
    fun insert(note: Note){
        InsertNoteTask(noteDao).execute(note)


    }
    fun delete(note: Note){
        DeleteNoteTask(noteDao).execute(note)

    }
    fun deleteAllNotes(){
        DeleteNoteTask(noteDao).execute()

    }
    fun update(note: Note){
        UpdateNoteTask(noteDao).execute(note)

    }
    fun findNoteById(id:Int):Note{


        //FindNoteTask(noteDao).execute(id)
        note=noteDao.findNoteById(id)
        Log.e("YYYY","title:"+note.title+note.description)
        return note

    }
    fun getAllNotes():LiveData<List<Note>>{
        return allNotes
    }
    companion object{
        class InsertNoteTask(private var noteDao: NoteDao):AsyncTask<Note,Void,Void>(){
            override fun doInBackground(vararg params: Note?): Void? {
                noteDao.addNote(note = params[0] as Note)
                return null
            }

        }
        class UpdateNoteTask(private var noteDao: NoteDao):AsyncTask<Note,Void,Void>(){
            override fun doInBackground(vararg params: Note?): Void? {
                noteDao.updateNote(note = params[0] as Note)
                return null
            }

        }
        class DeleteNoteTask(private var noteDao: NoteDao):AsyncTask<Note,Void,Void>(){
            override fun doInBackground(vararg params: Note?): Void? {
                noteDao.deleteNote(note = params[0] as Note)
                return null
            }

        }
        class DeleteAllNotesTask(private var noteDao: NoteDao):AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                noteDao.deleteAllNotes()
                return null
            }

        }


    }

     inner class FindNoteTask(private var noteDao: NoteDao):AsyncTask<Int,Void,Int>(){
        private var note:Note = Note("","",0)
        override fun doInBackground(vararg params: Int?): Int? {
            note=noteDao.findNoteById(id = (params[0]!!) )
            return 2
        }

        override fun onPostExecute(result: Int) {
            handleType(note)
        }

    }
     fun handleType(n:Note):Note{
         note=n
         Log.e("YYYY","title:"+note.title+note.description)
         return note
    }

}