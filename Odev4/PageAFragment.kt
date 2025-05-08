package com.example.navigationapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class PageAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page_a, container, false)

        view.findViewById<Button>(R.id.buttonToB).setOnClickListener {
            findNavController().navigate(R.id.action_pageA_to_pageB)
        }

        return view
    }
}
