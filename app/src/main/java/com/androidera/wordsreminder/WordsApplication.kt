package com.androidera.wordsreminder

import android.app.Application
import com.androidera.wordsreminder.data.WordRoomDatabase
import com.androidera.wordsreminder.repository.WordRepository

class WordsApplication : Application() {
    val database: WordRoomDatabase by lazy { WordRoomDatabase.getDatabase(this) }
    val repository: WordRepository by lazy { WordRepository(database.wordDao()) }
}