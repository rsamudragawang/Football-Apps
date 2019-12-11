package com.ganargatul.football_apps.ui.main.detailteam

import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.PastEventReponse
import com.ganargatul.football_apps.test.repository.detailpastevent.DetailPastEventRepository
import com.ganargatul.football_apps.test.repository.detailpastevent.DetailPastEventRepositoryCallback
import com.ganargatul.football_apps.test.repository.detailteam.DetailTeamRepository
import com.ganargatul.football_apps.test.repository.detailteam.DetailTeamRepositoryCallback

class DetailTeamPresenter(private val view: DetailTeamView, private val detailTeamRepository: DetailTeamRepository) {

    fun loaddata(id: String){
        view.showLoading()
//        d("presenter","presenter")

        detailTeamRepository.getNextMatch(id,object :
            DetailTeamRepositoryCallback<DetailTeamsResponse?> {
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