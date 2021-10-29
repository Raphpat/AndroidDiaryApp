package com.diaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TextEditorFragment : Fragment() {

    //TODO Position the text boxes better and add prompts

    private val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private lateinit var noteTitle : EditText
    private lateinit var noteContent : EditText

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
        val view = inflater.inflate(R.layout.text_editor_fragment, container, false)
        val dateReminder = view.findViewById<TextView>(R.id.dateReminder)
        noteTitle = view.findViewById(R.id.noteTitle)
        noteContent = view.findViewById(R.id.noteContent)
        val saveButton = view.findViewById<FloatingActionButton>(R.id.floatingSaveButton)

        // Add an observer to the date in ViewModel, so that when it changes the date text can also be updated
        viewModel.getSelectedDate().observe(this) { date ->
            dateReminder.text = dateFormat.format(date)
        }

        // Set the date
        viewModel.setSelectedDate(LocalDate.now())

        // Bind the save button to the save note action
        saveButton.setOnClickListener {
            addNewNote()
        }

        return view
    }

    // Check if none of the note fields are empty
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            noteTitle.text.toString(),
            noteContent.text.toString()
        )
    }

    // Save the note and return to the main screen
    private fun addNewNote() {
        if (isEntryValid()) {
            viewModel.addNewNote(
                noteTitle.text.toString(),
                noteContent.text.toString()
            )
            activity?.finish()
        }
    }
}

