package com.yunuscagliyan.androidcomponentexample.fragments


import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.yunuscagliyan.androidcomponentexample.R
import com.yunuscagliyan.androidcomponentexample.data.Note
import com.yunuscagliyan.androidcomponentexample.data.NoteViewModel
import com.yunuscagliyan.androidcomponentexample.databinding.FragmentAunoteBinding
import kotlinx.android.synthetic.main.fragment_aunote.*

/**
 * A simple [Fragment] subclass.
 */
class AUNoteFragment : Fragment() {

    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var auNoteBinding: FragmentAunoteBinding
    private lateinit var model: NoteViewModel
    private  var noteID=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auNoteBinding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_aunote,container,false)
        return auNoteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model=ViewModelProviders.of(this@AUNoteFragment).get(NoteViewModel::class.java)

        var note=Note("","",0)
        arguments.let {
            var safeArgs=
                AUNoteFragmentArgs.fromBundle(
                    it!!
                )
            noteID=safeArgs.noteId

        }
        if (noteID>0){
            note=model.findNote(noteID)
            etTitle.setText(note.title)
            etDescription.setText(note.description)
            spinner.setSelection(note.priority-1)
        }
        btSave.setOnClickListener {
            var title=auNoteBinding.etTitle.text.toString()
            var description=auNoteBinding.etDescription.text.toString()
            var priority=Integer.parseInt(auNoteBinding.spinner.selectedItem.toString())


            if(noteID<=0){
                note= Note(title,description,priority)
                model.insertNote(note)
                Toast.makeText(context,"Note Added",Toast.LENGTH_LONG).show()
            }else{
                note.title=title
                note.description=description
                note.priority=priority
                model.updateNote(note)
                Log.e("NNNN",note.title+note.description+"priority:"+note.priority)
                Toast.makeText(context,"Note Updated",Toast.LENGTH_LONG).show()
            }


            etTitle.setText("")
            etDescription.setText("")
        }




    }


}
