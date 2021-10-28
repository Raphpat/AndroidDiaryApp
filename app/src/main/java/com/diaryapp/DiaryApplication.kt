package com.diaryapp

import android.app.Application
import com.db.data.NoteRoomDatabase

class DiaryApplication : Application(){
    val database: NoteRoomDatabase by lazy { NoteRoomDatabase.getDatabase(this) }
}