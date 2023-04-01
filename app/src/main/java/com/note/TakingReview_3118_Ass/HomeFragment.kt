package com.note.TakingReview_3118_Ass

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.note.TakingReview_3118_Ass.MainActivity.Companion.db
import com.note.TakingReview_3118_Ass.adapter.NoteAdapter
import com.note.TakingReview_3118_Ass.databinding.DialogDeleteBinding
import com.note.TakingReview_3118_Ass.databinding.DialogEditBinding
import com.note.TakingReview_3118_Ass.databinding.DialogInsertBinding
import com.note.TakingReview_3118_Ass.databinding.FragmentHomeBinding
import com.note.TakingReview_3118_Ass.room.entity.Note

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding

    private lateinit var noteListener: NoteAdapter.Companion.NoteListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater,container,false)

        initElement()
        loadNote()
        return bind.root
    }
    private fun initElement(){

        bind.rvNote.layoutManager = LinearLayoutManager(requireContext())
        val insertDialog = insertDialog()
        bind.fabAdd.setOnClickListener{
            insertDialog.show()
        }
        noteListener = object :NoteAdapter.Companion.NoteListener{
            override fun delete(note: Note) {
                deleteDialog(note).show()
            }
            override fun edit(note: Note) {
                editDialog(note).show()
            }
        }
    }
    private fun insertDialog():Dialog{
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val bind = DialogInsertBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)
        bind.btnInput.setOnClickListener{
            if(db.noteDao().insert(Note(0,bind.etJudul.text.toString(),bind.etCatatan.text.toString()))>=0){
                Toast.makeText(requireContext(),"Berhasil menambahkan catatan",Toast.LENGTH_SHORT).show()
                loadNote()
                dialog.dismiss()
            }else{
                Toast.makeText(requireContext(),"Gagal menambahkan catatan",Toast.LENGTH_SHORT).show()
            }
        }
        return dialog
    }
    private fun editDialog(note:Note):Dialog{
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val bind = DialogEditBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)
        bind.etJudul.setText(note.judul)
        bind.etCatatan.setText(note.catatan)
        bind.btnInput.setOnClickListener{
            note.judul=bind.etJudul.text.toString()
            note.catatan=bind.etCatatan.text.toString()
            if(db.noteDao().update(note)>0){
                Toast.makeText(requireContext(),"Berhasil mengubah catatan",Toast.LENGTH_SHORT).show()
                loadNote()
                dialog.dismiss()
            }else{
                Toast.makeText(requireContext(),"Gagal mengubah catatan",Toast.LENGTH_SHORT).show()
            }
        }
        return dialog
    }
    private fun deleteDialog(note:Note):Dialog{
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val bind = DialogDeleteBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)
        bind.btnCancel.setOnClickListener{
            dialog.dismiss()
        }
        bind.btnHapus.setOnClickListener{
            if(db.noteDao().delete(note)>0){
                Toast.makeText(requireContext(),"Berhasil menghapus catatan",Toast.LENGTH_SHORT).show()
                loadNote()
            }else{
                Toast.makeText(requireContext(),"Berhasil menghapus catatan",Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        return dialog
    }

    private fun loadNote(){
        bind.rvNote.adapter = NoteAdapter(db.noteDao().getNote(),noteListener)
    }
}