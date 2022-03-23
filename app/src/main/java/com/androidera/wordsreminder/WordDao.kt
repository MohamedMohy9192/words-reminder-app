package com.androidera.wordsreminder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    /**
     * When Room queries return Flow, the queries are automatically run asynchronously on a background thread.
     */
    @Query("SELECT * FROM word ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    /**
     * There is no convenience annotation for deleting multiple entities, so it's annotated with the generic @Query.
     */
    @Query("DELETE FROM word")
    suspend fun deleteAll()
}