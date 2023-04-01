package com.note.TakingReview_3118_Ass

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.note.TakingReview_3118_Ass.databinding.ActivityMainBinding
import com.note.TakingReview_3118_Ass.room.AppDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        db = AppDatabase.db(applicationContext)
        sharedPref = getSharedPreferences("logged", Context.MODE_PRIVATE)
        //val navHostFragment = bind.navHostFragment as NavHostFragment
        //val navController = navHostFragment.navController
    }
    companion object{
        lateinit var db: AppDatabase
        lateinit var sharedPref: SharedPreferences
        val gson:Gson = Gson()
    }
}