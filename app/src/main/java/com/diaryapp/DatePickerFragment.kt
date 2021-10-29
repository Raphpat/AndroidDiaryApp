package com.diaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import java.time.LocalDate

class DatePickerFragment : Fragment() {

    // Store the view model
    private val viewModel: FragmentViewModel by activityViewModels{
        FragmentViewModelFactory(
            (activity?.application as DiaryApplication).database
                .noteDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.date_picker_fragment, container, false)

        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        // Add an event listener so that when it is changed the date in the TextEditorFragment is updated
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            val date = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
            viewModel.setSelectedDate(date)
        }
        return view
    }
}