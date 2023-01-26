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

//    private var book: BookList? = null


//    init {
//        view.setOnClickListener {
//            Toast.makeText(it.context, "нажата ${it.id}", Toast.LENGTH_SHORT).show()
//        }
//    }

//    fun bind(book: BookList?) {
//        if (book == null) {
//            val resources = itemView.resources
//            title.text = resources.getString(R.string.loading)
//            description.text = resources.getString(R.string.unknown)
//            author.text = resources.getString(R.string.unknown)
//            publishedDate.text = resources.getString(R.string.unknown)
//
//        } else {
//            showBookData(book)
//        }
//    }

//    private fun showBookData(book: BookList) {
//        this.book = book
//        Log.d("ShowBook",book.id.toString())
//
//        title.text = book.volumeInfo.title
//
//
//        description.text = book.volumeInfo.description
//        var text = ""
//        if (book.volumeInfo.authors !=null) {
//            for (i in book.volumeInfo.authors.indices ) {
//                text = text.plus(book.volumeInfo.authors[i] + " ")
//            }
//            author.text = text
//        } else {
//            author.text = "неизвестный автор"
//        }
//
//        publishedDate.text = book.volumeInfo.publishedDate
//      Picasso.get().load(book.volumeInfo.imageLinks?.thumbnail).into(bookCover)
//
//    }
}