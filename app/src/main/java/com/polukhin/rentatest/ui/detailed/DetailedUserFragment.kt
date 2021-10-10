package com.polukhin.rentatest.ui.detailed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.polukhin.rentatest.R
import com.polukhin.rentatest.model.User

class DetailedUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_detailed_user, container, false)

        val image: ImageView = root.findViewById(R.id.image_view_detailed)
        val firstName: TextView = root.findViewById(R.id.first_name_detailed)
        val lastName: TextView = root.findViewById(R.id.last_name_detailed)
        val email: TextView = root.findViewById(R.id.email_detailed)
        val backButton: ImageView = root.findViewById(R.id.detailed_fab)

        if (arguments?.isEmpty == false) {
            val user: User = arguments?.getSerializable("bundle_user") as User
            Log.d("BUNDLE_USER", user.toString())
            firstName.text = user.first_name
            lastName.text = user.last_name
            email.text = user.email

            Glide.with(root.context)
                .load(user.avatar)
                .into(image)
        } else {
            Log.d("BUNDLE_USER", "Error get bundle")
        }

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return root
    }
}