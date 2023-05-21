package com.note.TakingReview_3118_Ass.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.note.TakingReview_3118_Ass.MainActivity.Companion.db
import com.note.TakingReview_3118_Ass.R
import com.note.TakingReview_3118_Ass.databinding.FragmentRegisterBinding
import com.note.TakingReview_3118_Ass.room.entity.User

class RegisterFragment : Fragment() {
    private lateinit var bind: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRegisterBinding.inflate(inflater,container,false)
        initElement()
        return bind.root
    }
    private fun initElement(){
        bind.btnRegister.setOnClickListener{
            if(db.userDao().checkDuplicate(bind.etEmail.text.toString())==0) {
                if (db.userDao().insert(
                        User(bind.etEmail.text.toString(),
                            bind.etUsername.text.toString(),
                            bind.etPassword.text.toString())
                    )
                    >=0){
                    Toast.makeText(requireContext(),"Registrasi berhasil",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }else{
                    Toast.makeText(requireContext(),"Gagal melakukan registrasi",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(),"Email telah dipakai",Toast.LENGTH_SHORT).show()
            }
        }
    }
}