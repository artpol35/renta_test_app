package com.polukhin.rentatest.db

import androidx.room.*
import com.polukhin.rentatest.model.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllUsers(users: List<User>)

    @Query("DELETE FROM users")
    fun deleteAllUsers()
}