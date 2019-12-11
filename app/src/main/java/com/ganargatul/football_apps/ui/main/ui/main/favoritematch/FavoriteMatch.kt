package com.ganargatul.football_apps.ui.main.ui.main.favoritematch


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.adapter.FavoriteAdapter
import com.ganargatul.football_apps.db.MatchFavoriteItems
import com.ganargatul.football_apps.db.database
import com.ganargatul.football_apps.ui.main.detailfavorite.DetailFavorite
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatch : Fragment() {
    private var favorites: MutableList<MatchFavoriteItems> = mutableListOf()
    lateinit var recyclerview: RecyclerView
    lateinit var adapter: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_favorite_match, container, false)
        recyclerview= root.findViewById(R.id.rv_list_fav)
        recyclerview.layoutManager= LinearLayoutManager(context)
        initData()
        return root
    }
    private fun initData(){
        favorites.clear()

        try {
            context?.database?.use {
                select(MatchFavoriteItems.TABLE_FAVORITE).parseList(object  :
                    MapRowParser<List<MatchFavoriteItems>> {
                    override fun parseRow(columns: Map<String, Any?>): List<MatchFavoriteItems> {
                        val ID = columns.getValue(MatchFavoriteItems.ID)
                        val EVENT_ID = columns.getValue(MatchFavoriteItems.EVENT_ID)
                        val TEAM_HOME = columns.getValue(MatchFavoriteItems.TEAM_HOME_ID)
                        val TEAM_AWAY = columns.getValue(MatchFavoriteItems.TEAM_AWAY_ID)
                        val EVENT_NAME = columns.getValue(MatchFavoriteItems.EVENT_NAME)
                        val favorite = MatchFavoriteItems(
                            ID.toString().toInt(),
                            EVENT_ID.toString().toInt(),
                            TEAM_HOME.toString().toInt(),
                            TEAM_AWAY.toString().toInt(),
                            EVENT_NAME.toString()
                        )
                        favorites.add(favorite)
                        Log.d("fav", favorites.toString())
                        return favorites
                    }
                })
                showFav()

            }
        }catch (e : SQLiteConstraintException){
            e.printStackTrace()
        }
    }

    private fun showFav() {
        val adapter = context?.let {
            FavoriteAdapter(it,favorites){
                var nowItems = MatchFavoriteItems(it.ID_,it.EVENT_ID_,it.TEAM_HOME_,it.TEAM_AWAY_,it.EVENT_NAME_)
                startActivity<DetailFavorite>("Data" to nowItems)
                activity!!.finish()

            }
        }
        recyclerview.adapter = adapter
    }

}
