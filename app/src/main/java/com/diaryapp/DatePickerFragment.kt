package com.diaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class DatePickerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.date_picker_fragment, container, false)

        //TODO pass the date
//        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
//                val fragment = TextEditorFragment.newInstance(getString(R.string.date, dayOfMonth, monthOfYear, year))
//            }
//        }
        return view
    }
}