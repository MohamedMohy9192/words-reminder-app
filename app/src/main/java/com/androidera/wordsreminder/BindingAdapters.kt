package com.androidera.wordsreminder

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidera.wordsreminder.adapter.WordListAdapter
import com.androidera.wordsreminder.data.Word

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, words: List<Word>) {
    val adapter = recyclerView.adapter as WordListAdapter
    adapter.submitList(words)
}