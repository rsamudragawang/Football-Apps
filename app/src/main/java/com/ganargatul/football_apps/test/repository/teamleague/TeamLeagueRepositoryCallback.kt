package com.ganargatul.football_apps.test.repository.teamleague

import com.ganargatul.football_apps.model.TeamItems

interface TeamLeagueRepositoryCallback<T> {
    fun onDataLoaded(data: List<TeamItems>)
    fun onDataError()
}