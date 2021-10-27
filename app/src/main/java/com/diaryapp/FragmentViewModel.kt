package com.diaryapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

/**
 * Class that allows exchanging of information between fragments
 */
class FragmentViewModel : ViewModel() {

    private val date = MutableLiveData<LocalDate>()

    fun setDate(message: LocalDate) {
        date.value = message
    }

    fun getDate() : MutableLiveData<LocalDate>{
        return date
    }
}