package com.aurumquotes.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aurumquotes.app.R
import com.aurumquotes.app.data.Result
import com.aurumquotes.app.databinding.QuotesItemBinding
import com.squareup.picasso.Callback
import java.lang.Exception

class QuotesAdapter(private val listener : Listener) : ListAdapter<Result,QuotesAdapter.LeaguesHolder>(Comparator()) {
    class LeaguesHolder(view:View) : RecyclerView.ViewHolder(view) {

        private val binding = QuotesItemBinding.bind(view)

        fun setFootballItemInfo(item : Result,listener : Listener) = with(binding){
            tvAuthor.text = item.author
            tvContent.text = item.content
            tvDate.text = item.dateAdded


            itemView.setOnClickListener {
                listener.onClickItem(item)

            }

        }

    }

    class Comparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem:Result, newItem:Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item,parent,false)
        return LeaguesHolder(view)
    }

    override fun onBindViewHolder(holder: LeaguesHolder, position: Int) {
       holder.setFootballItemInfo(getItem(position),listener)
    }


    interface Listener {
       fun onClickItem(item:Result)
    }
}