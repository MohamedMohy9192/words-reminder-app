package com.androidera.wordsreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidera.wordsreminder.adapter.WordListAdapter
import com.androidera.wordsreminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            lifecycleOwner = this@MainActivity
            wordRecyclerView.adapter = WordListAdapter()
            wordRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }
}