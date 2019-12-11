package com.ganargatul.football_apps.ui.main.searchevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.adapter.PastEventAdapter
import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.SearchItems
import com.ganargatul.football_apps.test.EspressoIdlingResource
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepository
import com.ganargatul.football_apps.ui.main.detailpastevent.DetailPastEvent
import org.jetbrains.anko.startActivity

class SearchEvent : AppCompatActivity(), SearchEventView {
    lateinit var recyclerview: RecyclerView
    lateinit var adapter: PastEventAdapter
    lateinit var items: PastEventItems
    lateinit var pb: ProgressBar
    private var listItems: MutableList<PastEventItems> = mutableListOf()
    lateinit var presenter: SearchEventPresenter
    lateinit var query : SearchItems
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_event)
        pb= findViewById(R.id.pb_next_event)
        pb.visibility = View.VISIBLE
        recyclerview= findViewById(R.id.rv_list_next_event)
        recyclerview.layoutManager= GridLayoutManager(baseContext,2)
        query  = intent.getParcelableExtra("query")
        presenter = SearchEventPresenter(this, SearchEventRepository())
        EspressoIdlingResource.increment()
        presenter.loadData(query.query)
    }
    override fun showLoading() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = View.GONE
    }

    override fun showTeamList(data: List<PastEventItems>) {
        for (i in data.indices) {
//            d("listitems", data[i].strEvent)
            if (data[i].strSport == "Soccer" && data[i].idLeague  == query.id.toString()) {
//                d("soccer", data[i].strEvent)
                listItems.add(
                    PastEventItems(
                        data[i].idEvent,
                        data[i].strEvent,
                        data[i].dateEvent,
                        data[i].strTime,
                        data[i].idHomeTeam,
                        data[i].idAwayTeam,
                        data[i].strThumb,
                        data[i].strHomeGoalDetails,
                        data[i].strHomeRedCards,
                        data[i].strHomeYellowCards,
                        data[i].strAwayRedCards,
                        data[i].strAwayYellowCards,
                        data[i].strAwayGoalDetails,
                        data[i].intHomeScore,
                        data[i].intAwayScore,
                        data[i].strSport,
                        data[i].idLeague
                    )
                )
            }
            d("listitems", listItems.toString())

        }
        if (listItems.size > 0) {
            showItems(listItems)

        } else {
            Toast.makeText(this@SearchEvent, R.string.notfound, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDataLoaded(data: List<PastEventItems>) {
        for (i in data.indices) {
//            d("listitems", data[i].strEvent)
            if (data[i].strSport == "Soccer" && data[i].idLeague  == query.id.toString()) {
//                d("soccer", data[i].strEvent)
                listItems.add(
                    PastEventItems(
                        data[i].idEvent,
                        data[i].strEvent,
                        data[i].dateEvent,
                        data[i].strTime,
                        data[i].idHomeTeam,
                        data[i].idAwayTeam,
                        data[i].strThumb,
                        data[i].strHomeGoalDetails,
                        data[i].strHomeRedCards,
                        data[i].strHomeYellowCards,
                        data[i].strAwayRedCards,
                        data[i].strAwayYellowCards,
                        data[i].strAwayGoalDetails,
                        data[i].intHomeScore,
                        data[i].intAwayScore,
                        data[i].strSport,
                        data[i].idLeague
                    )
                )
            }
            d("listitems", listItems.toString())

        }
        if (listItems.size > 0) {
            showItems(listItems)

        } else {
            Toast.makeText(this@SearchEvent, R.string.notfound, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDataError() {
        pb.visibility = View.GONE
    }
    private fun showItems(it1: List<PastEventItems>) {

        adapter= PastEventAdapter(baseContext,it1){
            var nowItems = PastEventItems(it.idEvent,it.strEvent,it.dateEvent,it.strTime,it.idHomeTeam,it.idAwayTeam,
                it.strThumb,it.strHomeGoalDetails,it.strHomeRedCards,it.strHomeYellowCards,it.strAwayRedCards,it.strAwayYellowCards,
                it.strAwayGoalDetails,it.intHomeScore,it.intAwayScore,it.strSport,it.idLeague)
            startActivity<DetailPastEvent>("Data" to nowItems)
        }
        recyclerview.adapter = adapter
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            //Memberitahukan bahwa tugas sudah selesai dijalankan
            EspressoIdlingResource.decrement()
        }

    }
}
