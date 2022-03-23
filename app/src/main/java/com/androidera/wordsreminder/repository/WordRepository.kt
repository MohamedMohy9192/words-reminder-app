package com.androidera.wordsreminder.repository

import androidx.annotation.WorkerThread
import com.androidera.wordsreminder.data.Word
import com.androidera.wordsreminder.data.WordDao
import kotlinx.coroutines.flow.Flow

/**
 * A Repository manages queries and allows you to use multiple backends.
 * In the most common example, the Repository implements the logic for deciding whether to fetch
 * data from a network or use results cached in a local database.
 *
 *  Declares the DAO as a private property in the constructor. Pass in the DAO
 *  instead of the whole database, because you only need access to the DAO
 */
class WordRepository(private val wordDao: WordDao) {

    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    /**
     * By default Room runs suspend queries off the main thread, therefore, we don't need to
     * implement anything else to ensure we're not doing long running database work
     * off the main thread.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread // Denotes that the annotated method should only be called on a worker thread. (background thread)
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}