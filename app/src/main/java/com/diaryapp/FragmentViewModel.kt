package com.diaryapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Class that allows exchanging of information between fragments
 */
class FragmentViewModel : ViewModel() {

    private val date = MutableLiveData<String>()

    fun setDate(message: String) {
        date.value = message
    }

    fun getDate() : MutableLiveData<String>{
        return date
    }
}