package com.diaryapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.db.dao.NoteDao
import com.db.data.Note
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * Class that allows exchanging of information between fragments
 */
class FragmentViewModel(private val noteDao: NoteDao) : ViewModel() {

    // Current date for which the diary entry will be saved
    private val selectedDate = MutableLiveData<LocalDate>()

    fun setSelectedDate(message: LocalDate) {
        selectedDate.value = message
    }

    fun getSelectedDate() : MutableLiveData<LocalDate>{
        return selectedDate
    }

    // Insert a note into the database using a coroutine
    private fun insertNote(note : Note){
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }

    // Creates a new note with the given arguments
    private fun getNewNoteEntry(title: String, content: String, date: LocalDate): Note {
        return Note(
            title = title,
            content = content,
            date = date
        )
    }

    // Create and save to the database the new note
    fun addNewNote(title: String, content: String) {
        val newNote = getNewNoteEntry(title, content, selectedDate.value!!)
        insertNote(newNote)
    }

    // Check that none of the note fields are blank
    fun isEntryValid(title: String, content: String): Boolean {
        if (title.isBlank() || content.isBlank()) {
            return false
        }
        return true
    }
}

/**
 * Constructor for the FragmentViewModel that passes it a reference to the noteDao
 */
class FragmentViewModelFactory(private val noteDao: NoteDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FragmentViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}