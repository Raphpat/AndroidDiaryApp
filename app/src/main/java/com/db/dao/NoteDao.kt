package com.db.dao

import androidx.room.*
import com.db.data.Note
import kotlinx.coroutines.flow.Flow

/**
 * Manages the app's calls to the database for Note obejects
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
    fun getItemsByDateAsc(): Flow<List<Note>>

    @Query("SELECT * from note ORDER BY date DESC")
    fun getItemsByDateDesc(): Flow<List<Note>>

    @Query("SELECT * from note ORDER BY title ASC")
    fun getItemsByTitleAsc(): Flow<List<Note>>

    @Query("SELECT * from note ORDER BY title DESC")
    fun getItemsByTitleDesc(): Flow<List<Note>>
}