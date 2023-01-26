package com.example.mybookshelf.ui.book_detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybookshelf.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityBookDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
        private const val EXTRA_BOOK_ID = "bookId"

        fun newIntent(context: Context, bookId: String): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(EXTRA_BOOK_ID, bookId)
            return intent
        }
    }
}