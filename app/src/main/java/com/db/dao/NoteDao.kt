package com.db.dao

import androidx.room.*
import com.db.data.Note
import kotlinx.coroutines.flow.Flow

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
    fun getItems(): Flow<List<Note>>
}