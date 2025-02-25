package com.example.a15_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class BlankFragment_end : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View=inflater.inflate(R.layout.blank_end, container, false)
        // ============= Button обработчик, способ записи 1
        var btn: Button =view.findViewById(R.id.back_from_end_button)
        btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    p0.findNavController().navigate(R.id.action_blankFragment_end_pop)
                }
            }
        })
//        // ============= Button3 обработчик, способ записи 2
//        var btn3:Button=view.findViewById(R.id.to_2_button)
//        btn3.setOnClickListener(View.OnClickListener { view -> view.findNavController().navigate(R.id.action_blankFragment_to_blankFragment2) })
//        // ============= Button5 обработчик, способ записи 3
//        view.findViewById<Button>(R.id.to_3_button).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_blankFragment_to_blankFragment3))

        return view
    }
}