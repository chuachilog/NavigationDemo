package com.example.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ThankyouFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thankyou, container, false)

        val txt : TextView = view.findViewById(R.id.tvResult)

        val result = arguments?.getFloat("score")

        txt.text = "Result :" + String.format("%.2f",result) + "%"


        return view
    }
}