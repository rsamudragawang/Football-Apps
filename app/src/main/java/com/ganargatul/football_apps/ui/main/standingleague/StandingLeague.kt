package com.ganargatul.football_apps.ui.main.standingleague


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.adapter.StandingAdapter
import com.ganargatul.football_apps.model.LeagueItems
import com.ganargatul.football_apps.model.StandingItems
import com.ganargatul.football_apps.test.repository.standingleague.StandingLeagueRepository

/**
 * A simple [Fragment] subclass.
 */
class StandingLeague : Fragment(), StandingLeagueView {


    lateinit var recyclerview: RecyclerView
    lateinit var adapter: StandingAdapter
    lateinit var pb : ProgressBar
    lateinit var presenter: StandingLeaguePresenter
    lateinit var view: StandingLeagueView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_standing_league, container, false)
        pb= root.findViewById(R.id.pb_standing)
        recyclerview= root.findViewById(R.id.rv_standing)
        recyclerview.layoutManager= LinearLayoutManager(context)
        val items= activity?.intent?.getParcelableExtra<LeagueItems>("Data")
        presenter = StandingLeaguePresenter(this, StandingLeagueRepository())
        presenter.getStanding(items?.id.toString())
        return root
    }

    override fun showLoading() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = View.GONE

    }

    override fun showTeamList(data: List<StandingItems>) {
        show(data)
    }

    override fun onDataLoaded(data: List<StandingItems>) {
        show(data)
    }

    override fun onDataError() {
        pb.visibility = View.GONE
    }

    private fun show(data: List<StandingItems>){
        adapter = StandingAdapter(context!!,data){

        }
        recyclerview.adapter = adapter
    }
}
