package com.polukhin.rentatest.ui.menu

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.polukhin.rentatest.R
import com.polukhin.rentatest.ui.ConnectionNetwork
import com.polukhin.rentatest.ui.UserRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MenuManager(
    private val repository: MenuRepository,
    private val adapter: UserRecyclerAdapter,
    private val view: View
) {
    
    fun getData() {
        if (ConnectionNetwork.providesConnectionResult(view.context)) {
            getNetworkData()
        } else {
            getLocalData()
        }
    }

    private fun getNetworkData() {

        val progressBar: ProgressBar = view.findViewById(R.id.menu_progress_bar)
        progressBar.visibility = ProgressBar.VISIBLE

        val disposable1 = repository.getUsersNetwork()
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                progressBar.visibility = ProgressBar.INVISIBLE
                adapter.setData(response.data)
            }, { error ->
                Log.d("MENU_MANAGER", "Error at upload data from server")
                Log.d("MENU_MANAGER", error.toString())

                Toast.makeText(view.context, "Error at upload data", Toast.LENGTH_SHORT).show()
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
                adapter.setData(users)
            }, { er ->
                Log.d("MENU_MANAGER", "Error get data local database")
                Log.d("MENU_MANAGER", er.toString())

                Toast.makeText(view.context, "Error at upload data", Toast.LENGTH_SHORT).show()
            })

        Log.d("MENU_MANAGER", "Work with local data")
    }
}