package com.ganargatul.football_apps.ui.main.detailteam

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ganargatul.football_apps.MainActivity
import com.ganargatul.football_apps.R
import com.ganargatul.football_apps.db.ClubFavoriteItems
import com.ganargatul.football_apps.db.MatchFavoriteItems
import com.ganargatul.football_apps.db.database
import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.test.repository.detailteam.DetailTeamRepository
import com.ganargatul.football_apps.ui.main.FavoriteActivity
import com.ganargatul.football_apps.ui.main.detailfavorite.DetailFavorite
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class DetailTeam : AppCompatActivity(), DetailTeamView {


    lateinit var progressBar: ProgressBar
    lateinit var logo: ImageView
    lateinit var title : TextView
    lateinit var desc: TextView
    lateinit var stadium: TextView
    lateinit var presenter: DetailTeamPresenter
    lateinit var items: TeamItems
    private  var menuItem: Menu? = null
    private  var isFav : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        progressBar = findViewById(R.id.detail_team_pb)
        logo = findViewById(R.id.image_detail_team)
        title = findViewById(R.id.title_detail_team)
        desc = findViewById(R.id.desc_detail_team)
        stadium = findViewById(R.id.stadium_detail_team)
        presenter = DetailTeamPresenter(this, DetailTeamRepository())
        items = intent.getParcelableExtra("Data")
        presenter.loaddata(items.id.toString())
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(data: List<DetailTeamsItems>) {
        showw(data)
    }

    private fun showw(data: List<DetailTeamsItems>) {
        title.text = data[0].strTeam
        stadium.text = data[0].stadium
        desc.text = data[0].desc
        data[0].strTeamBadge.let { Picasso.get().load(it).into(logo) }
    }

    override fun onDataLoaded(data: List<DetailTeamsItems>) {
        showw(data)
    }

    override fun onDataError() {
        progressBar.visibility = View.GONE
    }

    private fun favState() {
        database.use {
            val result = select(ClubFavoriteItems.TABLE_FAVORITE)
                .whereArgs("(CLUB_ID = {id})",
                    "id" to items.id.toString())
            val favorite = result.parseList(classParser<ClubFavoriteItems>())
            Log.d("favorite", favorite.toString())
            if (favorite.isNotEmpty()) {
                isFav = true
                Log.d("menuitems", menuItem.toString())
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@DetailTeam, R.drawable.ic_favorite_black_24dp)
            }else{
                Log.d("menuitems", menuItem.toString())
                isFav = false
                menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this@DetailTeam, R.drawable.ic_favorite_border_black_24dp)

            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        favState()
        return true
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
                    ClubFavoriteItems.TABLE_FAVORITE, "(CLUB_ID = {id})",
                    "id" to items.id.toString())
            }
            Toast.makeText(this@DetailTeam,getString(R.string.success), Toast.LENGTH_SHORT).show()
//            startActivity<FavoriteActivity>()
            finish()

        } catch (e: SQLiteConstraintException){
//            snackbar(e.localizedMessage).show()
            Toast.makeText(this@DetailTeam,getString(R.string.error), Toast.LENGTH_SHORT).show()

        }
    }

    private fun addtofav() {
        try {
            d("fav",items.name)
            database.use {
                insert(
                    ClubFavoriteItems.TABLE_FAVORITE,
                    ClubFavoriteItems.CLUB_ID to items.id,
                    ClubFavoriteItems.CLUB_NAME to items.name,
                    ClubFavoriteItems.CLUB_BADGE to items.logo
                )
                Toast.makeText(this@DetailTeam,getString(R.string.success), Toast.LENGTH_SHORT).show()
                finish()

            }
        }catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
            Toast.makeText(this@DetailTeam,getString(R.string.error), Toast.LENGTH_SHORT).show()

            e.printStackTrace()
        }

    }
}
