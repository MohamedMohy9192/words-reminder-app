package com.androidera.wordsreminder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidera.wordsreminder.adapter.WordListAdapter
import com.androidera.wordsreminder.data.Word
import com.androidera.wordsreminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val newWordResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            insertWordOrShowToast(result)
        }

    private val viewModel: WordViewModel by viewModels {
        WordViewModelFactory(
            (application as WordsApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            mainActivity = this@MainActivity
            wordRecyclerView.adapter = WordListAdapter()
            wordRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun insertWordOrShowToast(result: ActivityResult) {
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val data = result.data
                data?.getStringExtra(EXTRA_REPLY)?.let { word ->
                    viewModel.insert(Word(word = word))
                }
            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(
                    this,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    fun startNewWordActivity() {
        newWordResult.launch(Intent(this, NewWordActivity::class.java))
    }

}