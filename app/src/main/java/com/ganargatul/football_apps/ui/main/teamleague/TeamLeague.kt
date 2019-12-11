package com.ganargatul.football_apps.ui.main.teamleague


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.adapter.TeamAdapter
import com.ganargatul.football_apps.model.LeagueItems
import com.ganargatul.football_apps.model.SearchItems
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.test.repository.teamleague.TeamLeagueRepository
import com.ganargatul.football_apps.ui.main.detailteam.DetailTeam
import com.ganargatul.football_apps.ui.main.searchteam.SearchTeam
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamLeague : Fragment(), TeamLeagueView {


    lateinit var recyclerview: RecyclerView
    lateinit var adapter: TeamAdapter
    lateinit var pb : ProgressBar
    lateinit var presenter: TeamLeaguePresenter
    lateinit var view: TeamLeagueView
    lateinit var items : LeagueItems

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_team_league, container, false)
        pb= root.findViewById(R.id.pb_team)
        recyclerview= root.findViewById(R.id.rv_team)
        recyclerview.layoutManager= LinearLayoutManager(context)
        items= activity!!.intent!!.getParcelableExtra("Data")
        presenter = TeamLeaguePresenter(this, TeamLeagueRepository())
        presenter.getStanding(items.id.toString())
        setHasOptionsMenu(true)

        return root
    }

    override fun showLoading() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
    }

    override fun showTeamList(data: List<TeamItems>) {
        showw(data)
    }

    override fun onDataLoaded(data: List<TeamItems>) {
        showw(data)
    }

    private fun showw(data: List<TeamItems>) {
        adapter = TeamAdapter(context!!,data){
            val nowItems = TeamItems(it.id,it.name,it.logo,it.idLeague)
            startActivity<DetailTeam>("Data" to nowItems)

        }
        recyclerview.adapter = adapter
    }

    override fun onDataError() {
        pb.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.toolbar_menu,menu)
        val searchManager = this.activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_menu).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val searchItems = SearchItems(items.id!!.toInt(),query)
                startActivity<SearchTeam>("query" to searchItems)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}
