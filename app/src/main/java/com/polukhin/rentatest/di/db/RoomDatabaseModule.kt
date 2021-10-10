package com.polukhin.rentatest.di.db

import android.app.Application
import androidx.room.Room
import com.polukhin.rentatest.db.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDatabaseModule(application: Application) {

    private var app = application

    @Singleton
    @Provides
    fun providesRoomDatabase(): UserDatabase {
        return Room.databaseBuilder(app, UserDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesUserDao(userDatabase: UserDatabase) = userDatabase.getUserDao()
}