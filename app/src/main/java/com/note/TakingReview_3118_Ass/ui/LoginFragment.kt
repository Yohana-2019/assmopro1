package com.note.TakingReview_3118_Ass.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.note.TakingReview_3118_Ass.MainActivity.Companion.db
import com.note.TakingReview_3118_Ass.MainActivity.Companion.gson
import com.note.TakingReview_3118_Ass.MainActivity.Companion.sharedPref
import com.note.TakingReview_3118_Ass.R
import com.note.TakingReview_3118_Ass.databinding.FragmentLoginBinding
import com.note.TakingReview_3118_Ass.room.entity.User

class LoginFragment : Fragment() {
    private lateinit var bind: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentLoginBinding.inflate(inflater,container,false)
        sharedPref.getString("logged", null)?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        initElement()
        return bind.root
    }
    private fun initElement(){
        bind.tvDaftar.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        bind.btnLogin.setOnClickListener{
            val logged: User = db.userDao().login(
                bind.etEmail.text.toString(),
                bind.etPassword.text.toString())
            if(logged==null){
                Toast.makeText(requireContext(),"Email atau Password salah",Toast.LENGTH_SHORT).show()
            }else{
                with (sharedPref.edit()) {
                    putString("logged",gson.toJson(logged))
                    commit()
                }
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }
}