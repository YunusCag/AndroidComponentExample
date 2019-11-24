package com.yunuscagliyan.androidcomponentexample.data

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    companion object{
        @JvmStatic
        @Volatile private  var instance: NoteDatabase? =null
        private val LOCK=Any()
        fun getInstance(context: Context)= instance?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance=it }
        }
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
                NoteDatabase::class.java,"note_database")
                .addCallback(roomCallback)
                .allowMainThreadQueries()
                .build()
        private var roomCallback= object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(noteDao = instance!!.noteDao()).execute()
            }
        }
        class PopulateDbAsyncTask(private var noteDao: NoteDao):AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                Log.e("YYYY","Database Created")
                noteDao.addNote(Note("Note Title 1","Note Description 1",4))
                noteDao.addNote(Note("Note Title 2","Note Description 2",3))
                noteDao.addNote(Note("Note Title 3","Note Description 3",2))
                return null
            }

        }
    }
    abstract fun noteDao():NoteDao


}