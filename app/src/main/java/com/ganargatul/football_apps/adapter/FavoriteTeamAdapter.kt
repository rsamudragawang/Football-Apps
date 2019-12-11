package com.ganargatul.football_apps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.db.ClubFavoriteItems
import com.squareup.picasso.Picasso

class FavoriteTeamAdapter(private  val context: Context, private val items: List<ClubFavoriteItems>, private val listener: (ClubFavoriteItems) -> Unit): RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.team_name)
        private val image = view.findViewById<ImageView>(R.id.team_logo)
        fun bindItem(favoriteItems: ClubFavoriteItems, listener: (ClubFavoriteItems) -> Unit){
            name.text = favoriteItems.CLUB_NAME_
            favoriteItems.CLUB_BADGE_?.let { Picasso.get().load(it).into(image) }

            itemView.setOnClickListener { listener(favoriteItems) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTeamAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.team_items,parent,false))
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: FavoriteTeamAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

}