package com.diaryapp.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.db.dao.NoteDao
import com.db.data.Note
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime

/**
 * Class that allows exchanging of information between fragments
 */
class FragmentViewModel(private val noteDao: NoteDao) : ViewModel() {

    // Current date for which the diary entry will be saved
    private val selectedDate = MutableLiveData<LocalDateTime>()

    // Current note being loaded
    private val loadedNote = MutableLiveData<Note>()

//    private var selectedSort = MutableLiveData<Int>()

//    fun setSelectedSort(selected: Int) {
//        selectedSort.value = selected
//    }
//
//    fun getSelectedSort(): MutableLiveData<Int> {
//        return selectedSort
//    }

    fun setSelectedDate(message: LocalDateTime) {
        selectedDate.value = message
    }

    fun getSelectedDate(): MutableLiveData<LocalDateTime> {
        return selectedDate
    }

    fun getLoadedNote(): MutableLiveData<Note> {
        return loadedNote
    }

    // Insert a note into the database using a coroutine
    private fun insertNote(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }

    // Creates a new note with the given arguments
    private fun getNewNoteEntry(title: String, content: String, date: LocalDateTime): Note {
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

    fun getAllNotes(selectedSort: Int): LiveData<List<Note>> {

        return when (selectedSort) {
//            DATE_DESC -> noteDao.getItemsByDateDesc().asLiveData()
//            TITLE_ASC -> noteDao.getItemsByTitleAsc().asLiveData()
//            TITLE_DESC -> noteDao.getItemsByTitleDesc().asLiveData()
            1 -> {
                Log.w("VIEW MODEL", "Sorting by 1, date_desc")
                noteDao.getItemsByDateDesc().asLiveData()
            }
            else -> {
                Log.w("VIEW MODEL", "Sorting by default, date_asc")
                noteDao.getItemsByDateAsc().asLiveData()
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }

    fun updateNote(note: Note) {
        note.date = selectedDate.value!!
        viewModelScope.launch {
            noteDao.update(note)
        }
        resetLoadedNote()
    }

    fun loadNote(id: Int) {
        val note = noteDao.getItem(id)
        viewModelScope.launch {
            note.collect {
                loadedNote.value = it
            }
        }
    }

    fun resetLoadedNote() {
        loadedNote.value = null
    }

    companion object {
        const val DATE_ASC = 0
        const val DATE_DESC = 1
        const val TITLE_ASC = 2
        const val TITLE_DESC = 3
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