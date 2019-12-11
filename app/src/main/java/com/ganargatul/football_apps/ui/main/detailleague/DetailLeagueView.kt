package com.ganargatul.football_apps.ui.main.detailleague

import com.ganargatul.football_apps.model.LeagueDetailsItem
import com.ganargatul.football_apps.model.LeagueResponse
import com.ganargatul.football_apps.test.repository.detailleague.DetailLeagueRepositoryCallback

interface DetailLeagueView: DetailLeagueRepositoryCallback<LeagueResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<LeagueDetailsItem>)
}