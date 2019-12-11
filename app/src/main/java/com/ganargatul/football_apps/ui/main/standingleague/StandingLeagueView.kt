package com.ganargatul.football_apps.ui.main.standingleague

import com.ganargatul.football_apps.model.StandingItems
import com.ganargatul.football_apps.model.StandingResponse
import com.ganargatul.football_apps.test.repository.standingleague.StandingLeagueRepositoryCallback

interface StandingLeagueView : StandingLeagueRepositoryCallback<StandingResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<StandingItems>)
}