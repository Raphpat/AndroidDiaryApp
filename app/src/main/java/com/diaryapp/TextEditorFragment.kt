package com.diaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import java.text.SimpleDateFormat
import java.util.*

class TextEditorFragment : Fragment() {
    //TODO Add functionality to save button
    private val viewModel: FragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_editor_fragment, container, false)
        val dateReminder = view.findViewById<TextView>(R.id.dateReminder)
        dateReminder.text = SimpleDateFormat("dd/MM/yyyy").format(Date())

        // Add an observer to the date in ViewModel, so that when it changes the date text can also be updated
        viewModel.getDate().observe(this) { date ->
            dateReminder.text = date
        }

        return view

        //TODO save the note
    }
}

