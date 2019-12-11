package com.ganargatul.football_apps.ui.main.standingleague

import android.util.Log.d
import com.ganargatul.football_apps.model.StandingItems
import com.ganargatul.football_apps.model.StandingResponse
import com.ganargatul.football_apps.test.repository.standingleague.StandingLeagueRepository
import com.ganargatul.football_apps.test.repository.standingleague.StandingLeagueRepositoryCallback

class StandingLeaguePresenter(private val view: StandingLeagueView,private val standingLeagueRepository: StandingLeagueRepository) {
    fun getStanding(id : String){
        view.showLoading()
        standingLeagueRepository.standingLeague(id,object :
            StandingLeagueRepositoryCallback<StandingResponse?> {
            override fun onDataError() {
                view.hideLoading()
            }

            override fun onDataLoaded(data: List<StandingItems>) {
                view.hideLoading()
                view.showTeamList(data)
            }

        })
    }
}