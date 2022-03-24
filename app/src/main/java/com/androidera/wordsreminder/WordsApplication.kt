package com.androidera.wordsreminder

import android.app.Application
import com.androidera.wordsreminder.data.WordRoomDatabase
import com.androidera.wordsreminder.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    // The default behavior if an child job thrown an exception, it is ends up causing the parent
    // job that launch that child to fail and then all the jobs that are currently active within
    // that parent to fail a one way to get around this is by surrounding the job that
    // could throw and exception with try catch block that will prevent the parent job to fail
    // and prevent other jobs within the parent to fail or we can use a supervisor job

    // Children of a supervisor job can fail independently of each other.
    // A failure or cancellation of a child does not cause the supervisor job to fail and does
    // not affect its other children

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database: WordRoomDatabase by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository: WordRepository by lazy { WordRepository(database.wordDao()) }
}