package com.diaryapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.diaryapp.DiaryApplication
import com.diaryapp.viewModel.FragmentViewModel
import com.diaryapp.viewModel.FragmentViewModelFactory
import com.diaryapp.R
import java.time.LocalDateTime

class DatePickerFragment : Fragment() {

    // Store the view model
    private val viewModel: FragmentViewModel by activityViewModels {
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
            val date = LocalDateTime.of(year, monthOfYear + 1, dayOfMonth, 1, 1)
            viewModel.setSelectedDate(date)
        }

        viewModel.getLoadedNote().observe(this) { note ->
            if (note != null) {
                datePicker.updateDate(note.date.year, note.date.monthValue - 1, note.date.dayOfMonth)
            } else {
                viewModel.setSelectedDate(LocalDateTime.now())
            }
        }

        return view
    }
}