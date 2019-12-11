package com.ganargatul.football_apps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.db.MatchFavoriteItems

class FavoriteAdapter (private  val context: Context, private val items: List<MatchFavoriteItems>, private val listener: (MatchFavoriteItems) -> Unit): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.title_items)

        fun bindItem(favoriteItems: MatchFavoriteItems, listener: (MatchFavoriteItems) -> Unit){
            name.text = favoriteItems.EVENT_NAME_
            itemView.setOnClickListener { listener(favoriteItems) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.next_event_item,parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }
}