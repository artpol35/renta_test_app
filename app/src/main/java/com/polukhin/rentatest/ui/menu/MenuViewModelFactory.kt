package com.polukhin.rentatest.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polukhin.rentatest.ui.MenuContract

class MenuViewModelFactory(
    private val repository: MenuRepository,
    private val viewContract: MenuContract.IView
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MenuViewModel::class.java!!)) {
            MenuViewModel(this.repository, this.viewContract) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}