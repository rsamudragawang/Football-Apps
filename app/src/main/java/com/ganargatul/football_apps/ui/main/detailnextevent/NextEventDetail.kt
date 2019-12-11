package com.ganargatul.football_apps.ui.main.detailnextevent

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.db.MatchFavoriteItems
import com.ganargatul.football_apps.db.database
import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.NextEventsItems
import com.ganargatul.football_apps.test.repository.detailnextevent.DetailNextEventRepository
import com.ganargatul.football_apps.test.repository.getAwayBadge.GetAwayBadgeRepository
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class NextEventDetail : AppCompatActivity(), DetailNextEventView, GetAwayBadgeView {



    lateinit var eventName: TextView
    lateinit var eventDate: TextView
    lateinit var homeBadge: ImageView
    lateinit var awayBadge: ImageView
    lateinit var eventTime: TextView
    lateinit var progressbar: ProgressBar
    lateinit var items: NextEventsItems
    private  var menuItem: Menu? = null
    private  var isFav : Boolean = false
    lateinit var presenter: DetailNextEventPresenter
    lateinit var presenterAwayBadgeView: GetAwayBadgePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_next_event)

        progressbar = findViewById(R.id.detail_league_prog)
        progressbar.visibility = View.VISIBLE
        items= intent.getParcelableExtra("Data")
        eventName = findViewById(R.id.title_detail)
        eventDate = findViewById(R.id.date_detail)
        homeBadge = findViewById(R.id.homeBadge)
        awayBadge = findViewById(R.id.awayBadge)
        eventTime = findViewById(R.id.time_detail)
        presenter = DetailNextEventPresenter(this, DetailNextEventRepository())
        presenter.loaddata(items.idHomeTeam.toString())
        presenterAwayBadgeView = GetAwayBadgePresenter(this, GetAwayBadgeRepository())
    }
    private fun favState() {
        database.use {
            val result = select(MatchFavoriteItems.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to items.idEvent.toString())
            val favorite = result.parseList(classParser<MatchFavoriteItems>())
            d("favorite",favorite.toString())
            if (favorite.isNotEmpty()) {
                d("menuitems",menuItem.toString())
                isFav = true
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@NextEventDetail, R.drawable.ic_favorite_black_24dp)
            }else{
                isFav = false
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@NextEventDetail, R.drawable.ic_favorite_border_black_24dp)

            }
        }
    }

    private fun getAwayBadge(it1: List<DetailTeamsItems>) {
//        presenter.loaddata(items.idAwayTeam.toString())
        eventName.text=items.strEvent
        eventDate.text = items.dateEvent
        eventTime.text = items.strTime

        it1[0].strTeamBadge.let {  Picasso.get().load(it).into(homeBadge) }
        presenterAwayBadgeView.loaddata(items.idAwayTeam.toString())
    }
    override fun onBadgeAway(data: List<DetailTeamsItems>) {
        showww(data)
    }

    private fun showww(data: List<DetailTeamsItems>) {
        data[0].strTeamBadge.let { Picasso.get().load(it).into(awayBadge) }

    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }

    override fun showTeamList(data: List<DetailTeamsItems>) {
        getAwayBadge(data)
    }
    override fun onDataLoaded(data: List<DetailTeamsItems>) {
        getAwayBadge(data)

    }

    override fun onDataError() {
        progressbar.visibility = View.GONE
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        favState()
        return true
    }
    override fun show(data: List<DetailTeamsItems>) {
        showww(data)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
        return when(item.itemId){
            R.id.add_to_favorite -> {
               if(!isFav) addtofav() else deletefav()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deletefav() {
        try {
            database.use {
                delete(
                    MatchFavoriteItems.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to items.idEvent.toString())
            }
            Toast.makeText(this@NextEventDetail,getString(R.string.success),Toast.LENGTH_SHORT).show()
            finish()

        } catch (e: SQLiteConstraintException){
//            snackbar(e.localizedMessage).show()
            Toast.makeText(this@NextEventDetail,getString(R.string.error),Toast.LENGTH_SHORT).show()

        }
    }

    private fun addtofav() {
        try {
            database.use {
                insert(
                    MatchFavoriteItems.TABLE_FAVORITE,
                    MatchFavoriteItems.EVENT_ID to items.idEvent,
                    MatchFavoriteItems.TEAM_HOME_ID to items.idHomeTeam,
                    MatchFavoriteItems.TEAM_AWAY_ID to items.idAwayTeam,
                    MatchFavoriteItems.EVENT_NAME to items.strEvent
                )
            }
            Toast.makeText(this@NextEventDetail,getString(R.string.success),Toast.LENGTH_SHORT).show()
            finish()
        }catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
            Toast.makeText(this@NextEventDetail,getString(R.string.error),Toast.LENGTH_SHORT).show()

            e.printStackTrace()
        }

    }
}
