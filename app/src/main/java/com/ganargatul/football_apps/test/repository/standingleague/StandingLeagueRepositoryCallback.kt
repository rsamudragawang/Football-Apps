package com.ganargatul.football_apps.test.repository.standingleague

import com.ganargatul.football_apps.model.StandingItems

interface StandingLeagueRepositoryCallback<T> {
    fun onDataLoaded(data: List<StandingItems>)
    fun onDataError()
}