package com.polukhin.rentatest.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polukhin.rentatest.R
import com.polukhin.rentatest.di.app.App
import com.polukhin.rentatest.model.MenuRepository
import com.polukhin.rentatest.ui.ConnectionNetwork
import com.polukhin.rentatest.ui.MenuContract
import com.polukhin.rentatest.ui.UserRecyclerAdapter
import com.polukhin.rentatest.viewmodel.menu.MenuViewModel
import com.polukhin.rentatest.viewmodel.menu.MenuViewModelFactory
import javax.inject.Inject

class MenuFragment : Fragment(), MenuContract.IView {

    @Inject
    lateinit var repository: MenuRepository
    lateinit var progressBar: ProgressBar
    lateinit var adapter: UserRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity?.application as App).appComponent.injectMenu(this)

        val root = inflater.inflate(R.layout.fragment_menu, container, false)

        progressBar = root.findViewById(R.id.menu_progress_bar)

        val menuViewModel = ViewModelProvider(
            this, MenuViewModelFactory(
                repository,
                this
            )
        ).get(MenuViewModel::class.java)

        val recycler: RecyclerView = root.findViewById(R.id.recycler_menu_fragment)
        recycler.layoutManager = LinearLayoutManager(root.context)

        adapter = UserRecyclerAdapter(this.findNavController())
        recycler.adapter = adapter

        menuViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return root
    }

    override fun changeStateProgressBar(int: Int) {
        if (int == ProgressBar.VISIBLE) {
            progressBar.visibility = ProgressBar.VISIBLE
        } else {
            progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    override fun toastInfo(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun checkConnection(): Boolean {
        return ConnectionNetwork.providesConnectionResult(this.context)
    }
}