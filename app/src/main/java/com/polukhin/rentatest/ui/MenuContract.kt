package com.polukhin.rentatest.ui

import com.polukhin.rentatest.model.User

interface MenuContract {

    interface IView {

        fun changeStateProgressBar(int: Int)
        fun toastInfo(text: String)
        fun checkConnection(): Boolean
    }
}