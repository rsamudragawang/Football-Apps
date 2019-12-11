package com.ganargatul.football_apps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.model.TeamItems
import com.squareup.picasso.Picasso

class TeamAdapter (private  val context: Context, private val Items: List<TeamItems>, private val listener: (TeamItems) -> Unit): RecyclerView.Adapter<TeamAdapter.ViewHolder>()  {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.team_name)
        private val img = view.findViewById<ImageView>(R.id.team_logo)

        fun bindItem(items: TeamItems, listener: (TeamItems) -> Unit){
            name.text = items.name
            items.logo?.let { Picasso.get().load(it).into(img) }
            itemView.setOnClickListener { listener(items) }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.team_items,parent,false))
    }

    override fun getItemCount(): Int = Items.size

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        holder.bindItem(Items[position],listener)

    }


}