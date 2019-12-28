package com.yunuscagliyan.androidcomponentexample.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.stone.vega.library.VegaLayoutManager
import com.yunuscagliyan.androidcomponentexample.R
import com.yunuscagliyan.androidcomponentexample.SwipeController
import com.yunuscagliyan.androidcomponentexample.adapter.NoteAdapter
import com.yunuscagliyan.androidcomponentexample.data.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note_list.*

/**
 * A simple [Fragment] subclass.
 */
class NoteListFragment : Fragment() {

    private lateinit var model: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rVNoteList.layoutManager=StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL);
        rVNoteList.hasFixedSize()


        model=ViewModelProviders.of(this@NoteListFragment).get(NoteViewModel::class.java)
        var adapter=
            NoteAdapter(
                context,
                model
            )
        model.getAllNotes().observe(this, androidx.lifecycle.Observer {
            adapter.changeList(it)
        })
        rVNoteList.setHasFixedSize(true)
        var swipeController=
            SwipeController(adapter)
        var itemTouchHelper=ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(rVNoteList)
        rVNoteList.adapter=adapter
        rVNoteList.itemAnimator

    }


}
