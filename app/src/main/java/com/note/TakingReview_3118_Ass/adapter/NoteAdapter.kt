package com.note.TakingReview_3118_Ass.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.note.TakingReview_3118_Ass.databinding.RowNoteBinding
import com.note.TakingReview_3118_Ass.room.entity.Note

class NoteAdapter(private val list: List<Note>, private val listener: NoteListener)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    inner class ViewHolder(val bind: RowNoteBinding)
        : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val bind = RowNoteBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Note = list[position]
        with(viewHolder) {
            bind.tvJudul.text = item.judul
            bind.tvCatatan.text = item.catatan
            bind.ibDelete.setOnClickListener{
                listener.delete(item)
            }
            bind.ibEdit.setOnClickListener{
                listener.edit(item)
            }
        }
    }

    override fun getItemCount() = list.size

    companion object{
        interface NoteListener{
            fun delete(note:Note)
            fun edit(note:Note)
        }
    }
}