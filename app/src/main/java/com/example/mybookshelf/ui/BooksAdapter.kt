package com.example.mybookshelf.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.mybookshelf.model.BookList

class BooksAdapter: PagingDataAdapter<BookList, BookViewHolder>(BOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookItem = getItem(position)
        if (bookItem != null) {
            holder.bind(bookItem)
        }
    }


    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<BookList>() {
            override fun areItemsTheSame(oldItem: BookList, newItem: BookList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BookList, newItem: BookList): Boolean =
                oldItem == newItem
        }
    }



}