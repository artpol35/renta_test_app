package com.polukhin.rentatest.viewmodel.menu

import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polukhin.rentatest.model.MenuRepository
import com.polukhin.rentatest.model.User
import com.polukhin.rentatest.ui.MenuContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MenuViewModel(
    private val repository: MenuRepository,
    private val viewContract: MenuContract.IView
) : ViewModel() {

    private var users: MutableLiveData<List<User>> = MutableLiveData()

    fun getUsers(): LiveData<List<User>> {
        getData()
        return users
    }

    private fun getData() {
        if (viewContract.checkConnection()) {
            getNetworkData()
        } else {
            getLocalData()
        }
    }

    private fun getNetworkData() {

        viewContract.changeStateProgressBar(ProgressBar.VISIBLE)

        val disposable1 = repository.getUsersNetwork()
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                viewContract.changeStateProgressBar(ProgressBar.INVISIBLE)
                users.value = response.data
            }, { error ->
                Log.d("MENU_MANAGER", "Error at upload data from server")
                Log.d("MENU_MANAGER", error.toString())

                viewContract.toastInfo("Error at upload data")
            })

        val disposable2 = repository.getUsersNetwork()
            .subscribeOn(Schedulers.io())
            .subscribe({ resp ->
                repository.deleteAllUserLocal()
                repository.addAllUserLocal(resp.data)
            }, { er ->
                Log.d("MENU_MANAGER", "Error insert data to local database")
                Log.d("MENU_MANAGER", er.toString())
            })

        Log.d("MENU_MANAGER", "Work with network data")
    }

    private fun getLocalData() {

        val disposable3 = repository.getUserLocal().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                this.users.value = users
            }, { er ->
                Log.d("MENU_MANAGER", "Error get data local database")
                Log.d("MENU_MANAGER", er.toString())

                viewContract.toastInfo("Error at upload data")
            })

        Log.d("MENU_MANAGER", "Work with local data")
    }
}