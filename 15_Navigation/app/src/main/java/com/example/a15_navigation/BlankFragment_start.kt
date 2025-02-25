package com.example.a15_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class BlankFragment_start : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View=inflater.inflate(R.layout.blank_start, container, false)
        // ============= Button обработчик, способ записи 1
        var btn: Button =view.findViewById(R.id.to_1_button)
        btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    p0.findNavController().navigate(R.id.action_blankFragment_start_to_blankFragment_1)
                }
            }
        })
        // ============= Button3 обработчик, способ записи 2
        var btn3:Button=view.findViewById(R.id.to_2_button)
        btn3.setOnClickListener(View.OnClickListener { view -> view.findNavController().navigate(R.id.action_blankFragment_start_to_blankFragment_2) })
        // ============= Button5 обработчик, способ записи 3
        view.findViewById<Button>(R.id.to_3_button).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_blankFragment_start_to_blankFragment_3))
        return view
    }


}