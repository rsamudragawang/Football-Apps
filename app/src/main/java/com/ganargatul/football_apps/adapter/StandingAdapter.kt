package com.ganargatul.football_apps.adapter

import android.content.Context
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.model.StandingItems

class StandingAdapter(private  val context: Context, private val Items: List<StandingItems>,private val listener: (StandingItems) -> Unit): RecyclerView.Adapter<StandingAdapter.ViewHolder>()  {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.team_items_st)
        private val play = view.findViewById<TextView>(R.id.play_items_st)
        private val goal = view.findViewById<TextView>(R.id.goal_items_st)
        private val win = view.findViewById<TextView>(R.id.win_items_st)
        private val draw = view.findViewById<TextView>(R.id.draw_items_st)
        private val loss = view.findViewById<TextView>(R.id.loss_items_st)
        private val point = view.findViewById<TextView>(R.id.point_items_st)

        fun bindItem(items: StandingItems, listener: (StandingItems) -> Unit){
            name.text = items.teamName
            play.text = items.play.toString()
            goal.text = items.goal.toString()
            win.text = items.win.toString()
            draw.text = items.draw.toString()
            loss.text = items.loss.toString()
            point.text = items.point.toString()
//            itemView
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.standing_item,parent,false))
    }

    override fun getItemCount(): Int = Items.size

    override fun onBindViewHolder(holder: StandingAdapter.ViewHolder, position: Int) {
        holder.bindItem(Items[position],listener)
        d("adapter",Items.size.toString())

    }


}