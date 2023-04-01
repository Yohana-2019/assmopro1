package com.note.TakingReview_3118_Ass.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.note.TakingReview_3118_Ass.room.dao.NoteDao
import com.note.TakingReview_3118_Ass.room.entity.Note

@Database(entities = [ Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    companion object{
        fun db(ctx:Context):AppDatabase{
            return Room.databaseBuilder(
                ctx,
                AppDatabase::class.java, "NoteTaking"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}