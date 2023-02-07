package com.example.mybookshelf.ui.search_books.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.mybookshelf.R
import com.example.mybookshelf.domain.model.BookList
import com.squareup.picasso.Picasso

class BooksAdapter : PagingDataAdapter<BookList, BookViewHolder>(BOOK_COMPARATOR) {
    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<BookList>() {
            override fun areItemsTheSame(oldItem: BookList, newItem: BookList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BookList, newItem: BookList): Boolean =
                oldItem == newItem
        }
    }

    var onBookItemClickListener: ((BookList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.book_info_item, parent, false
        )
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookItem = getItem(position)
        if (bookItem == null) {
            val resources = holder.itemView.resources
            with(holder) {
                title.text = resources.getString(R.string.loading)
                description.text = resources.getString(R.string.unknown)
                author.text = resources.getString(R.string.unknown)
                publishedDate.text = resources.getString(R.string.unknown)
            }

        } else {
            with(holder) {
                title.text = bookItem.title
                description.text = bookItem.description
                author.text = bookItem.authors
                publishedDate.text = bookItem.publishedDate

                if (bookItem.imageLinks == null) {
                    bookCover.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
                } else {
                    Picasso.get().load(bookItem.imageLinks).into(bookCover)
                }
            }

        }

        holder.view.setOnClickListener {
            if (bookItem != null)
                onBookItemClickListener?.invoke(bookItem)
        }

    }

}