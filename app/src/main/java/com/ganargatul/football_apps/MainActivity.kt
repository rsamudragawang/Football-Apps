package com.ganargatul.football_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ganargatul.football_apps.adapter.LeagueAdapter
import com.ganargatul.football_apps.model.LeagueItems
import com.ganargatul.football_apps.ui.main.FavoriteActivity
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    private var items: MutableList<LeagueItems> = mutableListOf()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        startActivity<DetailLeague>("Data" to "data")
        recyclerView = findViewById(R.id.rv_league_grid)
        recyclerView.layoutManager = GridLayoutManager(baseContext,2)
        initData()

    }

    private fun initData() {
        val name = resources.getStringArray(R.array.name_league)
        val description = resources.getStringArray(R.array.description)
        val image = resources.obtainTypedArray(R.array.photo_league)
        val id = resources.getIntArray(R.array.id_league)
        items.clear()
        for (i in name.indices){
            items.add(LeagueItems(name[i],description [i],image.getResourceId(i,0),id[i]))
        }
        val adapter = applicationContext?.let {
            LeagueAdapter(it,items){
                if (it.id == 0){
                    startActivity<FavoriteActivity>()
                }else{
                    var nowItems = LeagueItems(it.name,it.description,it.image,it.id)
                    startActivity<DetailLeague>("Data" to nowItems)
                }


            }
        }
        recyclerView.adapter = adapter

    }

}
