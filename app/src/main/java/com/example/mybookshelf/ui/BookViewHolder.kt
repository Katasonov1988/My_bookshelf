package com.example.mybookshelf.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookshelf.R
import com.example.mybookshelf.model.BookList
import com.squareup.picasso.Picasso

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.book_title)
    private val author: TextView = view.findViewById(R.id.book_author)
    private val publishedDate: TextView = view.findViewById(R.id.book_publication_date)
    private val description: TextView = view.findViewById(R.id.book_description)
    private val bookCover: ImageView = view.findViewById(R.id.iv_book_cover)

    private var book: BookList? = null

    init {
        view.setOnClickListener {
            Toast.makeText(it.context, "нажата ${it.id}", Toast.LENGTH_SHORT).show()
        }
    }

    fun bind(book: BookList?) {
        if (book == null) {
            val resources = itemView.resources
            title.text = resources.getString(R.string.loading)
            description.text = resources.getString(R.string.unknown)
            author.text = resources.getString(R.string.unknown)
            publishedDate.text = resources.getString(R.string.unknown)

        } else {
            showBookData(book)
        }
    }

    private fun showBookData(book: BookList) {
        this.book = book
        Log.d("ShowBook",book.id.toString())

        title.text = book.volumeInfo.title


        description.text = book.volumeInfo.description
        var text = ""
        if (book.volumeInfo.authors !=null) {
            for (i in book.volumeInfo.authors.indices ) {
                text = text.plus(book.volumeInfo.authors[i] + " ")
            }
            author.text = text
        } else {
            author.text = "неизвестный автор"
        }

        publishedDate.text = book.volumeInfo.publishedDate
      Picasso.get().load(book.volumeInfo.imageLinks?.thumbnail).into(bookCover)

    }

    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.book_info_item, parent, false)
            return BookViewHolder(view)
        }
    }
}