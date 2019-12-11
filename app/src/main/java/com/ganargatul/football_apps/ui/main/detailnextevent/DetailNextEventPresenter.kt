package com.ganargatul.football_apps.ui.main.detailnextevent

import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.test.repository.detailnextevent.DetailNextEventRepository
import com.ganargatul.football_apps.test.repository.detailnextevent.DetailNextEventRepositoryCallback

class DetailNextEventPresenter(private val view: DetailNextEventView, private val pastEventRepository: DetailNextEventRepository) {

    fun loaddata(id: String){
        view.showLoading()
//        d("presenter","presenter")
        pastEventRepository.getNextMatch(id,object :
            DetailNextEventRepositoryCallback<DetailTeamsResponse?> {
            override fun onDataLoaded(data: List<DetailTeamsItems>) {
                view.hideLoading()
                view.showTeamList(data)
            }

            override fun onDataError() {
                view.hideLoading()
            }
        })
    }
}