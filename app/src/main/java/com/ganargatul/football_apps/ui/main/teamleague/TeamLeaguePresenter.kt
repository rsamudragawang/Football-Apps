package com.ganargatul.football_apps.ui.main.teamleague

import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.model.TeamResponse
import com.ganargatul.football_apps.test.repository.teamleague.TeamLeagueRepository
import com.ganargatul.football_apps.test.repository.teamleague.TeamLeagueRepositoryCallback

class TeamLeaguePresenter(private val view: TeamLeagueView,private val teamLeagueRepository: TeamLeagueRepository) {
    fun getStanding(id : String){
        view.showLoading()
        teamLeagueRepository.getTeam(id,object :
            TeamLeagueRepositoryCallback<TeamResponse?> {
            override fun onDataError() {
                view.hideLoading()
            }

            override fun onDataLoaded(data: List<TeamItems>) {
                view.hideLoading()
                view.showTeamList(data)
            }

        })
    }
}