package com.note.TakingReview_3118_Ass.room.dao

import androidx.room.*
import com.note.TakingReview_3118_Ass.room.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note WHERE email = :email")
    fun getNote(email: String): List<Note>

    @Insert
    fun insert(note: Note):Long

    @Update
    fun update(note: Note):Int

    @Delete
    fun delete(note: Note):Int

}