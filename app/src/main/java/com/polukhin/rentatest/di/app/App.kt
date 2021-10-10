package com.polukhin.rentatest.di.app

import android.app.Application
import com.polukhin.rentatest.di.db.RoomDatabaseModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder()
                .roomDatabaseModule(RoomDatabaseModule(this))
                .build()
    }
}