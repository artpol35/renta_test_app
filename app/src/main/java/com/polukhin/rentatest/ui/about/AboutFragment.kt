package com.polukhin.rentatest.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.polukhin.rentatest.R

class AboutFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val root = inflater.inflate(R.layout.fragment_about, container, false)

    val textView: TextView = root.findViewById(R.id.text_about_fragment)
    textView.text = getString(R.string.about_text)

    return root
  }
}