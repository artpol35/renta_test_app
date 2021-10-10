package com.polukhin.rentatest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.polukhin.rentatest.R
import com.polukhin.rentatest.model.User

class UserRecyclerAdapter(private val navController: NavController) :
    RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    private var users: List<User> = ArrayList()

    fun setData(newData: List<User>) {
        users = newData
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName: TextView = view.findViewById(R.id.card_view_first_name)
        val latsName: TextView = view.findViewById(R.id.card_view_last_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstName.text = users[position].first_name
        holder.latsName.text = users[position].last_name

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("bundle_user", users[position])
            navController.navigate(R.id.action_navigation_menu_to_detailedUserFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}