package com.example.a16_navigation_drawer.ui.about_application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutApplicationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is about application Fragment"
    }
    val text: LiveData<String> = _text
}