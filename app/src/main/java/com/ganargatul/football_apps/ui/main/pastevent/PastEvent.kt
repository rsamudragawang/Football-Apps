package com.ganargatul.football_apps.ui.main.pastevent


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.adapter.PastEventAdapter
import com.ganargatul.football_apps.model.LeagueItems
import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.SearchItems
import com.ganargatul.football_apps.test.repository.pastevent.PastEventRepository
import com.ganargatul.football_apps.ui.main.detailpastevent.DetailPastEvent
import com.ganargatul.football_apps.ui.main.searchevent.SearchEvent
import org.jetbrains.anko.support.v4.startActivity
/**
 * A simple [Fragment] subclass.
 */
class PastEvent : Fragment(), PastEventView {

    lateinit var recyclerview: RecyclerView
    lateinit var adapter: PastEventAdapter

    lateinit var pb : ProgressBar
    lateinit var presenter: PastEventPresenter
    lateinit var view: PastEventView
    lateinit var items: LeagueItems
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_past_event, container, false)
        setHasOptionsMenu(true)
        pb= root.findViewById(R.id.pb_past_event)
        recyclerview= root.findViewById(R.id.rv_past_event)
        recyclerview.layoutManager= GridLayoutManager(context,2)
        items= activity!!.intent!!.getParcelableExtra("Data")
        presenter = PastEventPresenter(this, PastEventRepository())
        presenter.loaddata(items.id.toString())
        return root
    }

    private fun showItems(it1: List<PastEventItems>) {
        adapter= PastEventAdapter(context!!,it1){
            var nowItems = PastEventItems(it.idEvent,it.strEvent,it.dateEvent,it.strTime,it.idHomeTeam,it.idAwayTeam,
                it.strThumb,it.strHomeGoalDetails,it.strHomeRedCards,it.strHomeYellowCards,it.strAwayRedCards,it.strAwayYellowCards,
                it.strAwayGoalDetails,it.intHomeScore,it.intAwayScore,it.strSport,it.idLeague)
            startActivity<DetailPastEvent>("Data" to nowItems)
        }

        recyclerview.adapter = adapter
    }
    override fun showLoading() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
    }

    override fun showTeamList(data: List<PastEventItems>) {
        showItems(data)
    }

    override fun onDataLoaded(data: List<PastEventItems>) {
        showItems(data)
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
                startActivity<SearchEvent>("query" to searchItems)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }


}
