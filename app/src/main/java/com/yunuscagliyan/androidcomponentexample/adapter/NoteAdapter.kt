package com.yunuscagliyan.androidcomponentexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yunuscagliyan.androidcomponentexample.data.Note
import com.yunuscagliyan.androidcomponentexample.data.NoteViewModel
import com.yunuscagliyan.androidcomponentexample.databinding.ItemOneNoteBinding
import com.yunuscagliyan.androidcomponentexample.fragments.NoteListFragmentDirections

class NoteAdapter(private var context: Context?,
                  private var viewModel: NoteViewModel):
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private   var allNotes:List<Note>
    init {
        allNotes= mutableListOf()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var inflatter=LayoutInflater.from(context)
        var itemBinding=ItemOneNoteBinding.inflate(inflatter,parent,false)

        return NoteViewHolder(
            itemBinding
        )

    }

    override fun getItemCount(): Int {
        if(allNotes==null){
            return 0
        }
        return allNotes.size
    }
    fun deleteNote(position: Int){
        var note= allNotes[position]
        viewModel.deleteNote(note)

    }
    fun addNote(){
        var note= Note("Example Title ","Example Description",4)
        viewModel.insertNote(note)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var note=allNotes.get(position)
        holder.bindNote(note)
    }
    fun changeList(newNoteList: List<Note>){
        allNotes=newNoteList
        notifyDataSetChanged()
    }
    class NoteViewHolder(private var itemBinding:ItemOneNoteBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bindNote(note:Note){
            itemBinding.note=note
            itemBinding.executePendingBindings()
            itemBinding.root.setOnClickListener {
                var action=
                    NoteListFragmentDirections.actionNext()
                action.noteId=note.noteId
                Navigation.findNavController(it).navigate(action)

            }
        }
    }
}