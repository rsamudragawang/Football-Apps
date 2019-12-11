package com.ganargatul.football_apps.ui.main.detailleague


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.model.LeagueDetailsItem
import com.ganargatul.football_apps.model.LeagueItems
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepository
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class League : Fragment(), DetailLeagueView {

    lateinit var name : TextView
    lateinit var desc : TextView
    lateinit var img : ImageView
    lateinit var website: TextView
    lateinit var progressbar : ProgressBar
    lateinit var presenter: DetailLeaguePresenter
    lateinit var items: LeagueItems
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_league, container, false)
        progressbar = root.findViewById(R.id.detail_league_prog)
        progressbar.visibility = View.VISIBLE
        items= activity?.intent?.getParcelableExtra("Data")!!
        name = root.findViewById(R.id.title_detail)
        desc = root.findViewById(R.id.desc_detail)
        img = root.findViewById(R.id.image_detail)
        website = root.findViewById(R.id.website_detail)
        presenter = DetailLeaguePresenter(this, DetailLeagueRepository())
        presenter.loadData(items.id.toString())
        return root
    }
    private fun show(data: List<LeagueDetailsItem>?) {
        items.image?.let { Picasso.get().load(it).into(img) }
        name.text = items.name
        desc.text = data?.get(0)?.strDescriptionEN
        website.text = data?.get(0)?.strWebsite
    }
    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility =View.GONE
    }

    override fun showTeamList(data: List<LeagueDetailsItem>) {
        show(data)
    }

    override fun onDataLoaded(data: List<LeagueDetailsItem>?) {
        show(data)
    }

    override fun onDataError() {
        progressbar.visibility =View.GONE
    }


}
