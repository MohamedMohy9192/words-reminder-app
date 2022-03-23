package com.androidera.wordsreminder

import androidx.lifecycle.*
import com.androidera.wordsreminder.data.Word
import com.androidera.wordsreminder.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val wordRepository: WordRepository) : ViewModel() {

    /**
     * Unlike Flow, LiveData is lifecycle aware, meaning that it will respect the lifecycle of
     * other components like Activity or Fragment. LiveData automatically stops or resumes
     * observation depending on the lifecycle of the component that listens for changes.
     * This makes LiveData the perfect component to be used for for changeable data that
     * the UI will use or display.
     */
    val allWords: LiveData<List<Word>> = wordRepository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch { wordRepository.insert(word) }
}

class WordViewModelFactory(private val wordRepository: WordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(wordRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}