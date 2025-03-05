package com.example.a16_navigation_drawer.ui.about_application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a16_navigation_drawer.databinding.FragmentAboutApplicationBinding
import com.example.a16_navigation_drawer.ui.about_application.AboutApplicationViewModel

class AboutApplicationFragment: Fragment() {

    private var _binding: FragmentAboutApplicationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(AboutApplicationViewModel::class.java)

        _binding = FragmentAboutApplicationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAboutApplication
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}