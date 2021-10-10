package com.polukhin.rentatest.di.app

import com.polukhin.randevutest.di.retrofit.RetrofitModule
import com.polukhin.rentatest.di.db.RoomDatabaseModule
import com.polukhin.rentatest.ui.menu.MenuFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RoomDatabaseModule::class])
interface AppComponent {

    fun injectMenu(menuFragment: MenuFragment)
}