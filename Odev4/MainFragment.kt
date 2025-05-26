package com.example.navigationapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<Button>(R.id.buttonToA).setOnClickListener {
            findNavController().navigate(R.id.action_main_to_pageA)
        }

        view.findViewById<Button>(R.id.buttonToX).setOnClickListener {
            findNavController().navigate(R.id.action_main_to_pageX)
        }

        return view
    }
}
