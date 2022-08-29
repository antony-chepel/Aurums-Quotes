package com.aurumquotes.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurumquotes.app.data.QuotesResponse

class QuotesModel : ViewModel() {
    val listQuotes = MutableLiveData<QuotesResponse>()
}