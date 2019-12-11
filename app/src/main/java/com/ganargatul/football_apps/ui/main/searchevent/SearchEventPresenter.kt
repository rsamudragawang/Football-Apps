package com.ganargatul.football_apps.ui.main.searchevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.SearchEventResponse
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepository
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepositoryCallback

class SearchEventPresenter(private val view: SearchEventView, private val NextEventRepository: SearchEventRepository) {
    fun loadData(idEvent: String){
            view.showLoading()
            NextEventRepository.getNextMatch(idEvent,object :
                SearchEventRepositoryCallback<SearchEventResponse?> {
                override fun onDataLoaded(data: List<PastEventItems>) {
                    view.hideLoading()
                    view.showTeamList(data)
                }


                override fun onDataError() {
                    view.hideLoading()
                }
            })



    }
}