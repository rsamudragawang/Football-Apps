package com.ganargatul.football_apps.ui.main.detailleague

import com.ganargatul.football_apps.model.LeagueDetailsItem
import com.ganargatul.football_apps.model.LeagueResponse
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepository
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepositoryCallback

class DetailLeaguePresenter(private val view: DetailLeagueView, private val detailLeagueRepository: DetailLeagueRepository) {
    fun loadData(idEvent: String) {
        view.showLoading()
        detailLeagueRepository.getNextMatch(idEvent, object :
            DetailLeagueRepositoryCallback<LeagueResponse?> {
            override fun onDataLoaded(data: List<LeagueDetailsItem>?) {
                view.hideLoading()
                data?.let { view.showTeamList(it) }
            }

            override fun onDataError() {
                view.hideLoading()
            }

        })
    }
}