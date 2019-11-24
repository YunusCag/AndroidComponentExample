package com.yunuscagliyan.androidcomponentexample

import android.graphics.*
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.RecyclerView

class SwipeController(private var adapter: NoteAdapter): ItemTouchHelper.Callback() {

    private var p:Paint = Paint()
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            0,
            LEFT or ItemTouchHelper.RIGHT
        )
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var position=viewHolder.adapterPosition
        if(direction==LEFT){
            adapter.deleteNote(position)
        }else{
            adapter.addNote()
            Log.e("Swipe","LEFT")

        }

    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
            var itemView=viewHolder.itemView
            var height:Float=((itemView.bottom).toFloat()-(itemView.top).toFloat())
            var width:Float=height/3
            if(dX>0){
                p.color = Color.parseColor("#388E3C")
                var background =  RectF(itemView.left.toFloat(),itemView.top.toFloat(), dX,itemView.bottom.toFloat())
                c.drawRect(background,p)
                var icon = BitmapFactory.decodeResource(recyclerView.context.resources, R.drawable.ic_done);
                var icon_dest =  RectF(itemView.left.toFloat() + width ,itemView.top.toFloat() + width,itemView.left.toFloat()+ 2*width,itemView.bottom.toFloat() - width)
                c.drawBitmap(icon,null,icon_dest,p)
            }else{
                p.color = Color.parseColor("#D32F2F")
                 var background =  RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat());
                 c.drawRect(background,p)
                 var icon = BitmapFactory.decodeResource(recyclerView.context.resources, R.drawable.ic_delete);
                 var icon_dest =  RectF(itemView.right.toFloat() - 2*width ,itemView.top.toFloat() + width,itemView.right.toFloat() - width,itemView.bottom.toFloat() - width);
                 c.drawBitmap(icon,null,icon_dest,p)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }
}