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
            // monthOfYear starts at 0 so add one, and if it needs a 0 infront add it
            var month = (monthOfYear + 1).toString()
            if (month.length < 2) {
                month = "0$month"
            }
            viewModel.setDate(getString(R.string.date, dayOfMonth, month, year))
        }
        return view
    }
}