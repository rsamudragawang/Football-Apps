package com.ganargatul.football_apps.ui.main.searchteam

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.SearchEventResponse
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.model.TeamResponse
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepository
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepositoryCallback
import com.ganargatul.football_apps.test.repository.searchteam.SearchTeamRepository
import com.ganargatul.football_apps.test.repository.searchteam.SearchTeamRepositoryCallback

class SearchTeamPresenter(private val view: SearchTeamView, private val searchTeamRepository: SearchTeamRepository) {
    fun loadData(idEvent: String){
            view.showLoading()
            searchTeamRepository.getNextMatch(idEvent,object :
                SearchTeamRepositoryCallback<TeamResponse?> {
                override fun onDataLoaded(data: List<TeamItems>) {
                    view.hideLoading()
                    view.showTeamList(data)
                }


                override fun onDataError() {
                    view.hideLoading()
                }
            })



    }
}