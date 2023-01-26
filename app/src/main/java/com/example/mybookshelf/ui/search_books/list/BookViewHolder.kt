package com.example.mybookshelf.ui.search_books.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookshelf.R

class BookViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.book_title)
    val author = view.findViewById<TextView>(R.id.book_author)
    val publishedDate = view.findViewById<TextView>(R.id.book_publication_date)
    val description = view.findViewById<TextView>(R.id.book_description)
    val bookCover = view.findViewById<ImageView>(R.id.iv_book_cover)

}