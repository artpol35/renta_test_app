package com.polukhin.rentatest.model

import com.polukhin.randevutest.di.retrofit.RetrofitAPI
import com.polukhin.rentatest.db.UserDao
import com.polukhin.rentatest.db.UserDatabase
import com.polukhin.rentatest.model.Response
import com.polukhin.rentatest.model.User
import io.reactivex.Single
import javax.inject.Inject


class MenuRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI,
    userDatabase: UserDatabase
) {

    private val userDao: UserDao = userDatabase.getUserDao()

    fun getUsersNetwork(): Single<Response> {
        return retrofitAPI.getUsers()
    }

    fun getUserLocal(): Single<List<User>> {
        return userDao.getAllUsers()
    }

    fun addAllUserLocal(users: List<User>) {
        userDao.addAllUsers(users)
    }

    fun deleteAllUserLocal() {
        userDao.deleteAllUsers()
    }
}