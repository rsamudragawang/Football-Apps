package com.ganargatul.football_apps.test.repository.searchteam


import com.ganargatul.football_apps.model.TeamItems


interface SearchTeamRepositoryCallback<T> {
    fun onDataLoaded(data: List<TeamItems>)
    fun onDataError()
}