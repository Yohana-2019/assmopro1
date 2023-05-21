package com.note.TakingReview_3118_Ass

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
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

    companion object {
        lateinit var db: AppDatabase
        lateinit var sharedPref: SharedPreferences
        val gson: Gson = Gson()
    }
    fun openwa(view: View) {
        val phoneNumber = "628228949689"
        val uri = Uri.parse("https://wa.me/$phoneNumber") // Intent URI untuk membuka WhatsApp
        val intent = Intent(Intent.ACTION_VIEW, uri) // Membuat Intent dengan tindakan ACTION_VIEW dan URI WhatsApp
        startActivity(intent) // Memulai Intent untuk membuka WhatsApp
    }
}