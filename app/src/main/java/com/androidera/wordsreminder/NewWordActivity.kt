package com.androidera.wordsreminder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.androidera.wordsreminder.databinding.ActivityNewWordBinding

const val EXTRA_REPLY = "com.androidera.wordsreminder.REPLY"

class NewWordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWordBinding.inflate(layoutInflater)

        binding.buttonSave.setOnClickListener {
            val replayIntent = Intent()
            if (binding.editWord.text.isBlank()) {
                setResult(Activity.RESULT_CANCELED, replayIntent)
            } else {
                replayIntent.putExtra(EXTRA_REPLY, binding.editWord.text.toString())
                setResult(Activity.RESULT_OK, replayIntent)
            }

            finish()
        }
    }
}