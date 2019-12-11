package com.ganargatul.football_apps.ui.main.detailnextevent

import com.ganargatul.football_apps.model.DetailTeamsItems
import com.ganargatul.football_apps.model.DetailTeamsResponse
import com.ganargatul.football_apps.test.repository.getAwayBadge.GetAwayBadgeRepository
import com.ganargatul.football_apps.test.repository.getAwayBadge.GetAwayBadgeRepositoryCallback

class GetAwayBadgePresenter(private val view: GetAwayBadgeView, private val pastEventRepository: GetAwayBadgeRepository) {

    fun loaddata(id: String){
//        d("presenter","presenter")
        pastEventRepository.getNextMatch(id,object :
            GetAwayBadgeRepositoryCallback<DetailTeamsResponse?> {
            override fun onBadgeAway(data: List<DetailTeamsItems>) {
                view.show(data)
            }

        })
    }
}