package com.aurumquotes.app.api

import com.aurumquotes.app.data.QuotesResponse
import retrofit2.Call
import retrofit2.http.GET

interface AurumsQuotesApi {


     @GET("quotes")
     fun getQuotes() : Call<QuotesResponse>
}