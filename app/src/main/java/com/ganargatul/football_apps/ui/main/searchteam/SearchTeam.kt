package com.ganargatul.football_apps.ui.main.searchteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.adapter.TeamAdapter
import com.ganargatul.football_apps.model.SearchItems
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.test.EspressoIdlingResource
import com.ganargatul.football_apps.test.repository.searchteam.SearchTeamRepository
import com.ganargatul.football_apps.ui.main.detailteam.DetailTeam
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class SearchTeam : AppCompatActivity(), SearchTeamView {


    lateinit var recyclerview: RecyclerView
    lateinit var adapter: TeamAdapter
    lateinit var pb: ProgressBar
    private var listItems: MutableList<TeamItems> = mutableListOf()
    lateinit var presenter: SearchTeamPresenter
    lateinit var query : SearchItems
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        pb= findViewById(R.id.pb_search_team)
        recyclerview= findViewById(R.id.rv_list_search_team)
        recyclerview.layoutManager= GridLayoutManager(baseContext,2)
        query  = intent.getParcelableExtra("query")
        presenter = SearchTeamPresenter(this, SearchTeamRepository())
        EspressoIdlingResource.increment()
        presenter.loadData(query.query)
    }

    override fun showLoading() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
    }

    override fun showTeamList(data: List<TeamItems>) {
        showTeam(data)
    }

    private fun showTeam(data: List<TeamItems>) {
        for(i in data.indices){
            if (data[i].idLeague == query.id.toString()){
                var nowitem = TeamItems(data[i].id.toString(),data[i].name.toString(),data[i].logo.toString(),data[i].idLeague.toString())
                listItems.add(nowitem)
            }
        }
        if (listItems.size > 0) {
            showItems(listItems)

        } else {
            Toast.makeText(this@SearchTeam, R.string.notfound, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showItems(listItems: MutableList<TeamItems>) {
        adapter = TeamAdapter(baseContext,listItems){
            val nowItems = TeamItems(it.id,it.name,it.logo,it.idLeague)
            startActivity<DetailTeam>("Data" to nowItems)

        }
        recyclerview.adapter = adapter
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            //Memberitahukan bahwa tugas sudah selesai dijalankan
            EspressoIdlingResource.decrement()
        }
    }

    override fun onDataLoaded(data: List<TeamItems>) {
        showTeam(data)
    }

    override fun onDataError() {
        pb.visibility = View.GONE
    }
}
