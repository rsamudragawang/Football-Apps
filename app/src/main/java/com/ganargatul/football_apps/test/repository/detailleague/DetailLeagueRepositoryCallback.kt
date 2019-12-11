package com.ganargatul.football_apps.test.repository.detailleague


import com.ganargatul.football_apps.model.LeagueDetailsItem


interface DetailLeagueRepositoryCallback<T> {
    fun onDataLoaded(data: List<LeagueDetailsItem>?)
    fun onDataError()
}