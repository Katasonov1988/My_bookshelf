package com.example.mybookshelf.ui.search_books.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.mybookshelf.R
import com.example.mybookshelf.data.model.BookList
import com.squareup.picasso.Picasso

class BooksAdapter : PagingDataAdapter<BookList, BookViewHolder>(BOOK_COMPARATOR) {
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
                title.text = bookItem.volumeInfo.title
                description.text = bookItem.volumeInfo.description
                var text = ""
                if (bookItem.volumeInfo.authors != null) {
                    for (i in bookItem.volumeInfo.authors.indices) {
                        text = text.plus(bookItem.volumeInfo.authors[i] + " ")
                    }
                    author.text = text
                } else {
                    author.text = "неизвестный автор"
                }

                publishedDate.text = bookItem.volumeInfo.publishedDate
                Picasso.get().load(bookItem.volumeInfo.imageLinks?.thumbnail).into(bookCover)
            }

        }

        holder.view.setOnClickListener {
            if (bookItem != null)
                onBookItemClickListener?.invoke(bookItem)
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