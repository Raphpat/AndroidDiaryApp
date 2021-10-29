package com.diaryapp

import android.app.Application
import com.db.data.NoteRoomDatabase

class DiaryApplication : Application(){
    // Initialises the database
    val database: NoteRoomDatabase by lazy { NoteRoomDatabase.getDatabase(this) }
}