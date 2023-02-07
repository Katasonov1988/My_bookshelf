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
import java.lang.IllegalArgumentException

class BookDetailActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_BOOK_ID = "bookId"
        private const val EMPTY_SYMBOL = ""
        fun newIntent(context: Context, bookId: String): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(EXTRA_BOOK_ID, bookId)
            return intent
        }
    }

    private var bookItemId = EMPTY_SYMBOL

    private lateinit var viewModel: BookDetailViewModel
    private val binding by lazy {
        ActivityBookDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        parseIntent()
        initViewModel()
        setDataToView()
    }

    private fun setDataToView() {
        viewModel.getBookDetaiItem(bookItemId)
        viewModel.bookItem.observe(this) {
            with(binding) {
                tvBookDetailTitle.text = it.title
                tvBookDetailAuthor.text = it.authors
                tvBookDetailPublishedDate.text =
                    resources?.getString(R.string.published_date, it.publishedDate)
                tvBookDetailPageCount.text =
                    resources?.getString(R.string.page_count, it.pageCount.toString())
                tvBookDetailLanguage.text = resources?.getString(R.string.language, it.language)
                tvBookDetailDescription.text = it.description
                if (it.imageLinks == null) {
                    ivBookDetailCover.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
                } else {
                    Picasso.get().load(it.imageLinks).into(ivBookDetailCover)
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            Injection.provideBookDetailViewModelFactory()
        )
            .get(BookDetailViewModel::class.java)
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_BOOK_ID)) {
            throw IllegalArgumentException("Param extra book id is absent")
        }
        if (intent.getStringExtra(EXTRA_BOOK_ID) != null) {
            bookItemId = intent.getStringExtra(EXTRA_BOOK_ID).toString()
            Log.d("BookDetail", bookItemId)
        }
    }
}