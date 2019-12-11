package com.ganargatul.football_apps.ui.main.detailpastevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.PastEventReponse
import com.ganargatul.football_apps.test.repository.detailpastevent.DetailPastEventRepository
import com.ganargatul.football_apps.test.repository.detailpastevent.DetailPastEventRepositoryCallback

class DetailPastEventPresenter(private val view: DetailPastEventView, private val pastEventRepository: DetailPastEventRepository) {

    fun loaddata(id: String){
        view.showLoading()
//        d("presenter","presenter")
        pastEventRepository.getNextMatch(id,object :
            DetailPastEventRepositoryCallback<PastEventReponse?> {
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