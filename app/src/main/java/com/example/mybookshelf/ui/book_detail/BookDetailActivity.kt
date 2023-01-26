package com.example.mybookshelf.ui.book_detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mybookshelf.Injection
import com.example.mybookshelf.R
import com.example.mybookshelf.databinding.ActivityBookDetailBinding
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {
    private var bookItemId = EMPTY_SYMBOL

    private lateinit var viewModel: BookDetailViewModel
    private val binding by lazy {
        ActivityBookDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        parseIntent()

        viewModel = ViewModelProvider(
            this,
            Injection.provideBookDetailViewModelFactory())
            .get(BookDetailViewModel::class.java)

        viewModel.getBookDetaiItem(bookItemId)
        viewModel.bookItem.observe(this){
            Log.d("BookDetail",it.toString())
            with(binding) {
                tvTitle.text = it.title
                tvAuthors.text = it.authors
                tvPublishedDate.text = it.publishedDate
                tvPageCount.text = it.pageCount.toString()
                tvLanguage.text = it.language
                tvDescription.text = it.description
                if  (it.imageLinks?.small == null) {
                    ivCoverBook.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
                } else {
                    Picasso.get().load(it.imageLinks.small).into(ivCoverBook)
                }

            }
        }

    }

    private fun parseIntent() {
        if(!intent.hasExtra(EXTRA_BOOK_ID)) {
            throw RuntimeException("Param extra book id is absent")
        }
        if (intent.getStringExtra(EXTRA_BOOK_ID) != null) {

            bookItemId = intent.getStringExtra(EXTRA_BOOK_ID).toString()
            Log.d("BookDetail",bookItemId)
        }
    }

    companion object {
        private const val EXTRA_BOOK_ID = "bookId"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, bookId: String): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(EXTRA_BOOK_ID, bookId)
            return intent
        }
    }

}