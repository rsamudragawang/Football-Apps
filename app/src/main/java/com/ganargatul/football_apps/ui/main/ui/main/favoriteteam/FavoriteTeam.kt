package com.ganargatul.football_apps.ui.main.ui.main.favoriteteam


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
import com.ganargatul.football_apps.adapter.FavoriteTeamAdapter
import com.ganargatul.football_apps.adapter.TeamAdapter
import com.ganargatul.football_apps.db.ClubFavoriteItems
import com.ganargatul.football_apps.db.database
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.ui.main.detailteam.DetailTeam
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeam : Fragment() {
    private var favorites: MutableList<ClubFavoriteItems> = mutableListOf()
    private var teamItems: MutableList<TeamItems> = mutableListOf()

    lateinit var recyclerview: RecyclerView
    lateinit var adapter: FavoriteTeamAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        recyclerview= root.findViewById(R.id.rv_list_fav)
        recyclerview.layoutManager= LinearLayoutManager(context)
        initData()
        return root
    }

    private fun initData() {
        favorites.clear()
        teamItems.clear()
        try {
            context?.database?.use {
                select(ClubFavoriteItems.TABLE_FAVORITE).parseList(object  :
                    MapRowParser<List<ClubFavoriteItems>> {
                    override fun parseRow(columns: Map<String, Any?>): List<ClubFavoriteItems> {
                        val ID = columns.getValue(ClubFavoriteItems.ID)
                        val CLUB_ID = columns.getValue(ClubFavoriteItems.CLUB_ID)
                        val TEAM_NAME = columns.getValue(ClubFavoriteItems.CLUB_NAME)
                        val BADGE = columns.getValue(ClubFavoriteItems.CLUB_BADGE)
                        val teamItem = TeamItems(
                            CLUB_ID.toString(),
                            TEAM_NAME.toString(),
                            BADGE.toString(),
                            "this is random"
                        )
                        val favorite = ClubFavoriteItems(
                            ID.toString().toInt(),
                            CLUB_ID.toString().toInt(),
                            TEAM_NAME.toString(),
                            BADGE.toString()
                        )
                        teamItems.add(teamItem)
                        favorites.add(favorite)
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
            TeamAdapter(it,teamItems){
                var nowItems = TeamItems(it.id,it.name,it.logo,it.idLeague)
                startActivity<DetailTeam>("Data" to nowItems)
                activity!!.finish()

            }
        }
        recyclerview.adapter = adapter
    }
}
