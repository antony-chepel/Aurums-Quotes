package com.aurumquotes.app.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aurumquotes.app.data.Result
import com.aurumquotes.app.databinding.ActivityDetailQuotesBinding

class DetailQuotes : AppCompatActivity() {
    private lateinit var binding: ActivityDetailQuotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding){

        val data = intent.getSerializableExtra("quotesdata") as Result

        tvAuthorDetail.text = data.author

        tvContentDetail.text = data.content
        imageView.setOnClickListener {
            finish()
        }

    }
}