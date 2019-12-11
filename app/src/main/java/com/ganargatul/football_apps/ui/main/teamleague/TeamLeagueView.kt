package com.ganargatul.football_apps.ui.main.teamleague

import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.model.TeamResponse
import com.ganargatul.football_apps.test.repository.teamleague.TeamLeagueRepositoryCallback

interface TeamLeagueView : TeamLeagueRepositoryCallback<TeamResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamItems>)
}