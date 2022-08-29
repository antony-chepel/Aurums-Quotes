package com.aurumquotes.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurumquotes.app.adapter.QuotesAdapter
import com.aurumquotes.app.api.RetrofitAurumsInstance
import com.aurumquotes.app.data.QuotesResponse
import com.aurumquotes.app.data.Result
import com.aurumquotes.app.databinding.ActivityMainBinding
import com.aurumquotes.app.screens.DetailQuotes
import com.aurumquotes.app.viewmodel.QuotesModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),QuotesAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val crs = CoroutineScope(Dispatchers.IO + Job())
    private val mainviewModel : QuotesModel by viewModels()
    private lateinit var adapter : QuotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRcViewQuotes()
        getQuotes()
        mainviewModel.listQuotes.observe(this,{
            adapter.submitList(it.results)
        })
    }

    private fun initRcViewQuotes() = with(binding){
        adapter = QuotesAdapter(this@MainActivity)
        rcQuotes.layoutManager = LinearLayoutManager(this@MainActivity,
            LinearLayoutManager.VERTICAL,false)
        rcQuotes.adapter = adapter

    }


    private fun getQuotes(){
        crs.launch {
            val data =  RetrofitAurumsInstance.api.getQuotes()

            data.enqueue(object : Callback<QuotesResponse> {
                override fun onResponse(
                    call: Call<QuotesResponse>,
                    response: Response<QuotesResponse>
                ) {
                    progressBar.visibility = View.GONE
                    mainviewModel.listQuotes.postValue(response.body())
                }

                override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {

                }

            })
        }
    }

    override fun onClickItem(item: Result) {
       startActivity(Intent(this@MainActivity,DetailQuotes::class.java)
           .putExtra("quotesdata",item)
       )
    }
}