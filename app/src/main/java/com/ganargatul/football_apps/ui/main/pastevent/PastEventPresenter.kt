package com.ganargatul.football_apps.ui.main.pastevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.PastEventReponse
import com.ganargatul.football_apps.test.repository.pastevent.PastEventRepository
import com.ganargatul.football_apps.test.repository.pastevent.PastEventRepositoryCallback

class PastEventPresenter(private val view: PastEventView,private val pastEventRepository: PastEventRepository) {
    fun loaddata(idEvent: String){
        view.showLoading()
        pastEventRepository.getNextMatch(idEvent,object :
            PastEventRepositoryCallback<PastEventReponse?> {
            override fun onDataError() {
                view.hideLoading()
            }

            override fun onDataLoaded(data: List<PastEventItems>) {
                view.hideLoading()
                view.showTeamList(data)
            }

        })
    }
}