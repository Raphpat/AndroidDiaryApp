package com.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.db.data.Note
import kotlinx.coroutines.flow.Flow

/**
 * Manages the apps calls to the database for Note obejcts
 */
@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * from note WHERE id = :id")
    fun getItem(id: Int): Flow<Note>

    @Query("SELECT * from note ORDER BY date ASC")
    fun getItems(): LiveData<List<Note>>
}