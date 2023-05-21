package com.note.TakingReview_3118_Ass.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.note.TakingReview_3118_Ass.room.entity.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun login(email: String, password:String): User

    @Query("SELECT COUNT(email) FROM user WHERE email = :email")
    fun checkDuplicate(email: String): Int

    @Insert
    fun insert(user: User):Long
}