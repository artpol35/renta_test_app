package com.polukhin.rentatest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.polukhin.rentatest.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
}