package com.ganargatul.football_apps.ui.main.nextevent


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.adapter.NextEventAdapter
import com.ganargatul.football_apps.model.LeagueItems
import com.ganargatul.football_apps.model.NextEventsItems
import com.ganargatul.football_apps.model.SearchItems
import com.ganargatul.football_apps.test.repository.nextevent.NextEventRepository
import com.ganargatul.football_apps.ui.main.detailnextevent.NextEventDetail
import com.ganargatul.football_apps.ui.main.searchevent.SearchEvent
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class NextEvent : Fragment(), NextEventView {

    lateinit var recyclerview: RecyclerView
    lateinit var adapter: NextEventAdapter

    lateinit var pb : ProgressBar
    lateinit var presenter: NextEventPresenter
    lateinit var view: NextEventView
    lateinit var items : LeagueItems
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_next_event, container, false)
        setHasOptionsMenu(true)

        pb= root.findViewById(R.id.pb_next_event)
        recyclerview= root.findViewById(R.id.rv_next_event)
        recyclerview.layoutManager= LinearLayoutManager(context)
        items= activity!!.intent!!.getParcelableExtra("Data")
        presenter = NextEventPresenter(this, NextEventRepository())
        presenter.loadData(items.id.toString())

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun showLoading() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
    }

    override fun showTeamList(data: List<NextEventsItems>) {
        showItems(data)
    }

    override fun onDataLoaded(data: List<NextEventsItems>) {
        showItems(data)
    }

    override fun onDataError() {
        pb.visibility = View.GONE
    }
    private fun showItems(it1: List<NextEventsItems>) {
        adapter= NextEventAdapter(context!!,it1){
            var nowItems = NextEventsItems(it.idEvent,it.strEvent,it.dateEvent,it.strTime,it.idHomeTeam,it.idAwayTeam)
            startActivity<NextEventDetail>("Data" to nowItems)
        }

        recyclerview.adapter = adapter
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
