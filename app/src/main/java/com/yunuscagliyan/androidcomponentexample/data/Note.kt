package com.yunuscagliyan.androidcomponentexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
 class Note{
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "note_id") var noteId:Int=0
    var title:String=""
    var description:String=""
    var priority:Int=0
    constructor(title: String,description: String,priority: Int){
        this.title=title
        this.description=description
        this.priority=priority
    }
}