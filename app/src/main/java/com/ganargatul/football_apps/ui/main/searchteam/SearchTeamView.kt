package com.ganargatul.football_apps.ui.main.searchteam

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.SearchEventResponse
import com.ganargatul.football_apps.model.TeamItems
import com.ganargatul.football_apps.model.TeamResponse
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepositoryCallback
import com.ganargatul.football_apps.test.repository.searchteam.SearchTeamRepositoryCallback


interface SearchTeamView  : SearchTeamRepositoryCallback<TeamResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamItems>)
}