package com.diaryapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import java.time.LocalDate

class DatePickerFragment : Fragment() {

    private val viewModel: FragmentViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.date_picker_fragment, container, false)

        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            val date = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
            viewModel.setDate(date)
        }
        return view
    }
}