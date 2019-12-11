package com.ganargatul.football_apps.ui.main.nextevent

import com.ganargatul.football_apps.model.NextEventResponse
import com.ganargatul.football_apps.model.NextEventsItems
import com.ganargatul.football_apps.test.repository.nextevent.NextEventRepository
import com.ganargatul.football_apps.test.repository.nextevent.NextEventRepositoryCallback

class NextEventPresenter(private val view: NextEventView,private val NextEventRepository: NextEventRepository) {
    fun loadData(idEvent: String){
        view.showLoading()

        NextEventRepository.getNextMatch(idEvent,object :
            NextEventRepositoryCallback<NextEventResponse?> {
            override fun onDataLoaded(data: List<NextEventsItems>) {
                view.hideLoading()
                view.showTeamList(data)
            }


            override fun onDataError() {
                view.hideLoading()
            }
        })
    }
}