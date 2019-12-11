package com.ganargatul.football_apps.ui.main.searchevent

import com.ganargatul.football_apps.model.PastEventItems
import com.ganargatul.football_apps.model.SearchEventResponse
import com.ganargatul.football_apps.test.repository.searchevent.SearchEventRepositoryCallback


interface SearchEventView  : SearchEventRepositoryCallback<SearchEventResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<PastEventItems>)
}