package com.polukhin.rentatest.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polukhin.rentatest.R
import com.polukhin.rentatest.di.app.App
import com.polukhin.rentatest.ui.UserRecyclerAdapter
import javax.inject.Inject

class MenuFragment : Fragment() {

    @Inject
    lateinit var repository: MenuRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity?.application as App).appComponent.injectMenu(this)

        val root = inflater.inflate(R.layout.fragment_menu, container, false)

        val recycler: RecyclerView = root.findViewById(R.id.recycler_menu_fragment)
        recycler.layoutManager = LinearLayoutManager(root.context)

        val adapter = UserRecyclerAdapter(this.findNavController())
        recycler.adapter = adapter

        val menuManager = MenuManager(repository, adapter, root)
        menuManager.getData()

        return root
    }
}